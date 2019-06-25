package it.shu2019.todos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import it.shu2019.utils.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.viewholder_todo.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    private val adapter: TodoAdapter = TodoAdapter()
    private var todoList: List<Todo> = emptyList()
        set(value) {
            field = value
            adapter.notifyDataSetChanged()
        }
    private lateinit var jsonService: JsonPlaceholderService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = this.adapter

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        jsonService = retrofit.create(JsonPlaceholderService::class.java)

        jsonService.getTodos().enqueue(object : Callback<List<Todo>> {

            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                toast("Si è verificato un errore")
            }

            override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
                this@MainActivity.todoList = response.body() ?: emptyList()
            }

        })
    }



    inner class TodoAdapter : RecyclerView.Adapter<TodoViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.viewholder_todo, parent, false)
            return TodoViewHolder(view)
        }

        override fun getItemCount(): Int = todoList.size

        override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
            val todo = todoList[position]
            holder.bind(todo)
        }

    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewId = itemView.textViewId
        val textViewContent = itemView.textViewContent
        val checkBox = itemView.checkBox

        fun bind(todo: Todo) {
            textViewId.text = "Id: ${todo.id} - UserId: ${todo.userId}"
            textViewContent.text = todo.title
            checkBox.isChecked = todo.completed
        }

    }

}
