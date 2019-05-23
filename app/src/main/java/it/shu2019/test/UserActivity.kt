package it.shu2019.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    private var email: String? = null
        set(value) {
            field = value
            textEmail.text = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        //val email = intent.extras.getString("email_key")
        //textEmail.text = email

        this.email = intent.extras.getString(EXTRA_EMAIL)
    }
}
