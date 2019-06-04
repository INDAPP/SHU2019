package it.shu2019.gallery

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import it.shu2019.utils.Images
import it.shu2019.utils.toast
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity(), ImageFragment.ImageFragmentListener {
    val imagesList = Images.getUrls(20)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)


        viewPager.adapter = ImageAdapter(supportFragmentManager)

    }

    override fun onImageFragmentClick(imageUrl: String?) {
        imageUrl?.let { toast(it) }
    }

    inner class ImageAdapter(fm: FragmentManager/*, val imagesList: List<String>*/)
        : FragmentPagerAdapter(fm) {
//        val imagesList: List<String>
//
//        init {
//            this.imagesList = imagesList
//        }

        override fun getItem(position: Int): Fragment {
            val imageUrl = imagesList[position]

            return ImageFragment.newInstance(imageUrl)
        }

        override fun getCount() = imagesList.size

    }

}
