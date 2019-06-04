package it.shu2019.gallery

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import it.shu2019.utils.Images
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.viewholder_image.view.*

class ListActivity : AppCompatActivity() {
    val imagesList = Images.getUrls(80, 512, 512)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerView.adapter = ImageListAdapter()
//        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    inner class ImageListAdapter : RecyclerView.Adapter<ImageViewHolder>() {

        override fun onCreateViewHolder(container: ViewGroup, type: Int): ImageViewHolder {
            val layoutInflater = LayoutInflater.from(container.context)
            val view = layoutInflater.inflate(R.layout.viewholder_image, container, false)
            val holder = ImageViewHolder(view)
            return holder
        }

        override fun getItemCount() = imagesList.size

        override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
            val urlString = imagesList[position]
            holder.bind(urlString)
        }

    }

    class ImageViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView

        init {
            this.imageView = itemView.imageView
        }

        fun bind(urlString: String) {
            Picasso.get().load(urlString).into(imageView)
        }

    }

}
