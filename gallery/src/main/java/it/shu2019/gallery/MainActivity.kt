package it.shu2019.gallery

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

const val REQUEST_IMAGE = 32423

class MainActivity : AppCompatActivity() {
    private var imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Pablo_picasso_1.jpg/1024px-Pablo_picasso_1.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener(this::openGallery)
        buttonList.setOnClickListener(this::openList)
    }

    override fun onResume() {
        super.onResume()
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container,
                ImageFragment.newInstance(imageUrl)
            )
            .commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_IMAGE -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.getStringExtra(EXTRA_IMAGE_URL)?.let {
                        this.imageUrl = it
                    }
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_camera -> {
                startActivity(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun openGallery(view: View) {
//        startActivity(
//            Intent(
//                this,
//                GalleryActivity::class.java
//            )
//        )
        startActivityForResult(
            Intent(
                this,
                GalleryActivity::class.java
            ),
            REQUEST_IMAGE
        )
    }

    fun openList(view: View) {
        startActivity(
            Intent(
                this,
                ListActivity::class.java
            )
        )
    }
}
