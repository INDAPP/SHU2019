package it.shu2019.test

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import it.shu2019.utils.preferences
import it.shu2019.utils.toast
import kotlinx.android.synthetic.main.activity_second.*

const val USER_EMAIL = "test@email.com"
const val USER_PASSWORD = "password123"

class LoginActivity : AppCompatActivity() {
//    private var preferences: SharedPreferences? = null
    val TAG = "ACTIVITY_LIFECYCLE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.i(TAG, "onCreate")

        button.setOnClickListener(this::onLoginClick)

//        preferences = getPreferences(Context.MODE_PRIVATE)

        val email = preferences.getString(PREFS_EMAIL, null)
        editTextMail.setText(email)
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }

    private fun onLoginClick(view: View) {
        val email = editTextMail.text.toString()
        val password = editTextPassword.text.toString()

        if (email == USER_EMAIL && password == USER_PASSWORD) {
            val intent = Intent(
                this,
                UserActivity::class.java
            )
            intent.putExtra(EXTRA_EMAIL, email)

            preferences
                .edit()
                .putString(PREFS_EMAIL, email)
                .apply()

            startActivity(intent)
        } else {
//            Toast.makeText(
//                this,
//                R.string.error_login,
//                Toast.LENGTH_SHORT
//            ).show()
            toast(R.string.error_login)
        }
    }

}
