package com.fada21.android.glidetest

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager

class ImagesAdapter(
    private val page: Int,
    private val glideRequester: RequestManager,
    private val seed: Int = 0
) :
    RecyclerView.Adapter<ImageVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val iv: ImageView = layoutInflater.inflate(R.layout.image_item, parent, false) as ImageView
        return ImageVH(iv)
    }

    override fun getItemCount(): Int = 100

    override fun onBindViewHolder(holder: ImageVH, position: Int) {
        val imageIndex = page * 100 + position
        holder.imageView.setOnClickListener {
            Toast.makeText(it.context, "image: $imageIndex", Toast.LENGTH_SHORT).show()
        }
        glideRequester.load("https://picsum.photos/seed/${imageIndex + seed}/1024/1024").into(holder.imageView)
    }
}

class ImageVH(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)