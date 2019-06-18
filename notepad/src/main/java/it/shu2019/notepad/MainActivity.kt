package it.shu2019.notepad

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import it.shu2019.utils.preferences
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.viewholder_note.view.*
import java.text.DateFormat
import java.util.*

const val REQUEST_NEW_NOTE = 2385
const val PREFS_NOTES = "PREFS_NOTES"

private val formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)

class MainActivity : AppCompatActivity() {
//    lateinit var notes: MutableList<String>
    val adapter = NoteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        this.notes = preferences.getStringSet(PREFS_NOTES, emptySet()).toMutableList()

        floatingActionButton.setOnClickListener(this::onNewNote)
        recyclerView.adapter = this.adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_NEW_NOTE -> {
                this.adapter.notifyDataSetChanged()
//                if (resultCode == Activity.RESULT_OK) {
//                    val text = data?.getStringExtra(EXTRA_CONTENT)
//                    onNewText(text)
//                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun onNewNote(view: View) {
        val intent = Intent(this, NewNoteActivity::class.java)

        startActivityForResult(intent, REQUEST_NEW_NOTE)
    }

//    private fun onNewText(text: String?) {
////        if (text == null || text.length == 0) return
//        val content = text?.takeIf { it.isNotBlank() } ?: return
//
//        this.notes.add(content)
//
//        this.adapter.notifyDataSetChanged()
//
//        preferences.edit()
//            .putStringSet(PREFS_NOTES, notes.toSet())
//            .apply()
//    }

    inner class NoteAdapter: RecyclerView.Adapter<NoteViewHolder>() {

        override fun onCreateViewHolder(container: ViewGroup, type: Int): NoteViewHolder {
            val layoutInflater = LayoutInflater.from(container.context)
            val view = layoutInflater.inflate(R.layout.viewholder_note, container, false)
            return NoteViewHolder(view)
        }

        override fun getItemCount(): Int = PostitRepository.getList().size //notes.size

        override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
//            val text = notes[position]
            val postit = PostitRepository.getList()[position]
            holder.bind(postit)
        }

    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.textView
        val textViewTitle: TextView = itemView.textViewTitle
        val textViewTime: TextView = itemView.textViewTime
        val cardView: CardView = itemView.cardView

        fun bind(postit: Postit) {
            textViewTitle.text = postit.title
            textView.text = postit.content
            val date = Date(postit.changed)
            textViewTime.text = formatter.format(date)
            cardView.setCardBackgroundColor(postit.color)
        }

    }

}
