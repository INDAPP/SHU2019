package it.shu2019.todos

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.realm.Realm
import it.shu2019.utils.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val ACTION_FETCH_TODOS = "it.shu2019.todos.action.FETCH_TODOS"

//private const val EXTRA_PARAM1 = "it.shu2019.todos.extra.PARAM1"
//private const val EXTRA_PARAM2 = "it.shu2019.todos.extra.PARAM2"

class TodosSyncService : IntentService("TodosSyncService"), Callback<List<Todo>> {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val jsonService = retrofit.create(JsonPlaceholderService::class.java)

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_FETCH_TODOS -> {
//                val param1 = intent.getStringExtra(EXTRA_PARAM1)
//                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                fetchTodos()
            }
        }
    }

    private fun fetchTodos() {
        jsonService.getTodos().enqueue(this)
    }

    override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
        Log.e("TodosSyncService", "Si è verificato un errore", t)
    }

    override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
        val todos = response.body() ?: return

        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            it.insertOrUpdate(todos)
        }
    }

    companion object {

        @JvmStatic
        fun startTodosFetch(context: Context) {
            val intent = Intent(context, TodosSyncService::class.java).apply {
                action = ACTION_FETCH_TODOS
//                putExtra(EXTRA_PARAM1, param1)
//                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }

    }
}
