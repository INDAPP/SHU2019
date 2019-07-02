package it.shu2019.todos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.RealmRecyclerViewAdapter
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.viewholder_todo.view.*

class MainActivity : AppCompatActivity() {
    private val adapter: TodoAdapter = TodoAdapter()
    private var todoList: List<Todo> = emptyList()
        set(value) {
            field = value
            adapter.notifyDataSetChanged()
        }
    private var todoRealmList: RealmResults<Todo>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = this.adapter

        val realm = Realm.getDefaultInstance()
        todoRealmList = realm.where(Todo::class.java).findAll()
        val realmAdapter = TodoRealmAdapter(todoRealmList)
        recyclerView.adapter = realmAdapter
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

    inner class TodoRealmAdapter(data: OrderedRealmCollection<Todo>?)
        : RealmRecyclerViewAdapter<Todo, TodoViewHolder>(data, true, true) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.viewholder_todo, parent, false)
            return TodoViewHolder(view)
        }

        override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
            val todo = todoRealmList?.get(position) ?: return
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
