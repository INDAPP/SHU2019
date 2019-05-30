package it.shu2019.gallery

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container,
                ImageFragment.newInstance("https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Pablo_picasso_1.jpg/1024px-Pablo_picasso_1.jpg")
            )
            .commit()

        button.setOnClickListener(this::openGallery)
    }

    fun openGallery(view: View) {
        startActivity(
            Intent(
                this,
                GalleryActivity::class.java
            )
        )
    }
}
