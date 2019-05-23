package it.shu2019.test

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*

const val USER_EMAIL = "test@email.com"
const val USER_PASSWORD = "password123"

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        button.setOnClickListener(this::onLoginClick)


    }

    private fun onLoginClick(view: View) {
        val email = editTextMail.text.toString()
        val password = editTextPassword.text.toString()

        if (email == USER_EMAIL && password == USER_PASSWORD) {
            val intent = Intent(
                this,
                UserActivity::class.java
            )
            intent.putExtra("email_key", email)
            startActivity(intent)
        } else {
            Toast.makeText(
                this,
                R.string.error_login,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
