package it.shu2019.notepad

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_new_note.*

const val EXTRA_CONTENT = "EXTRA_CONTENT"

class NewNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_new_note, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_done -> {
            onConfirm()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun onConfirm() {
        val text = editText.text.toString().trim()
        val intent = Intent()
        intent.putExtra(EXTRA_CONTENT, text)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}
