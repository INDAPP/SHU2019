package it.shu2019.gallery

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_image.*

private const val ARG_IMAGE_URL = "image_url"

class ImageFragment : androidx.fragment.app.Fragment() {
    private var imageUrl: String? = null
    private var listener: ImageFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageUrl = it.getString(ARG_IMAGE_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(imageUrl).into(imageView)
        imageView.setOnClickListener(this::onImageClick)
    }

    fun onImageClick(view: View) {
        listener?.onImageFragmentClick(imageUrl)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ImageFragmentListener
//        if (context is ImageFragmentListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement ImageFragmentListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface ImageFragmentListener {
        fun onImageFragmentClick(imageUrl: String?)
    }

    companion object {

//        @JvmStatic
//        fun newInstance2(imageUrl: String): ImageFragment {
//            val fragment = ImageFragment()
//            val bundle = Bundle()
//            bundle.putString(ARG_IMAGE_URL, imageUrl)
//            fragment.arguments = bundle
//            return fragment
//        }

        @JvmStatic
        fun newInstance(imageUrl: String) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_IMAGE_URL, imageUrl)
                }
            }

    }
}
