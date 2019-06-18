package it.shu2019.notepad

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_new_note.*

const val EXTRA_CONTENT = "EXTRA_CONTENT"

class NewNoteActivity : AppCompatActivity() {
    private var cardColor: Int = 0xFFFFFF

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        cardBlue.setOnClickListener(this::onColorClick)
        cardGreen.setOnClickListener(this::onColorClick)
        cardOrange.setOnClickListener(this::onColorClick)
        cardPurple.setOnClickListener(this::onColorClick)
        cardRed.setOnClickListener(this::onColorClick)
        cardYellow.setOnClickListener(this::onColorClick)
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

    private fun onColorClick(view: View) {
        val cardView = view as? CardView ?: return
        cardColor = cardView.cardBackgroundColor.defaultColor
        cardViewContent.setCardBackgroundColor(cardColor)
    }

    private fun onConfirm() {
        val title = editTextTitle.text.toString().trim()
        val content = editText.text.toString().trim()
        val time = System.currentTimeMillis()

        val postit = Postit(
            title,
            content,
            cardColor,
            time,
            time
        )

        PostitRepository.add(postit)

//        val intent = Intent()
//        intent.putExtra(EXTRA_CONTENT, text)
//        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}
