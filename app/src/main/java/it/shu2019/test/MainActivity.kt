package it.shu2019.test

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()/*, View.OnClickListener*/ {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val button = findViewById<Button>(R.id.button)

//        button.setOnClickListener(this)

        button.setOnClickListener(this::onButtonClick)

        textView.setText(R.string.text_view_message)

    }

    fun onButtonClick(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

//    override fun onClick(button: View) {
//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)
//    }


}
