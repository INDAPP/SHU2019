package it.shu2019.gallery

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import it.shu2019.utils.Images
import it.shu2019.utils.toast
import kotlinx.android.synthetic.main.activity_gallery.*

const val EXTRA_IMAGE_URL = "EXTRA_IMAGE_URL"

class GalleryActivity : AppCompatActivity(), ImageFragment.ImageFragmentListener {
    val imagesList = Images.getUrls(20)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)


        viewPager.adapter = ImageAdapter(supportFragmentManager)

    }

    override fun onImageFragmentClick(imageUrl: String?) {
        imageUrl?.let { toast(it) }

        val intent = Intent()
        intent.putExtra(EXTRA_IMAGE_URL, imageUrl)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    inner class ImageAdapter(fm: androidx.fragment.app.FragmentManager/*, val imagesList: List<String>*/)
        : androidx.fragment.app.FragmentPagerAdapter(fm) {
//        val imagesList: List<String>
//
//        init {
//            this.imagesList = imagesList
//        }

        override fun getItem(position: Int): androidx.fragment.app.Fragment {
            val imageUrl = imagesList[position]

            return ImageFragment.newInstance(imageUrl)
        }

        override fun getCount() = imagesList.size

    }

}
