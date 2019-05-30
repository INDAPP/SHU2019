package it.shu2019.gallery

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import it.shu2019.utils.Images

class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val imagesList = Images.getUrls(20)

    }
}
