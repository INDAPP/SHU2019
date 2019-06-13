package it.shu2019.notepad

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

const val REQUEST_NEW_NOTE = 2385

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingActionButton.setOnClickListener(this::onNewNote)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun onNewNote(view: View) {
        val intent = Intent(this, NewNoteActivity::class.java)

        startActivityForResult(intent, REQUEST_NEW_NOTE)
    }

}
