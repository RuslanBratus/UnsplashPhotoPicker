package com.example.unsplashphotopicker.fragments.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.unsplashphotopicker.R
import com.example.unsplashphotopicker.fragments.main.viewmodel.models.Photo

class MainPhotosAdapter (private val onClick: () -> Unit): RecyclerView.Adapter<PhotoViewHolder>() {
    var photos : List<Photo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        placeProducts(holder, position)
    }


    override fun getItemCount(): Int = photos.size

    private fun placeProducts(holder: PhotoViewHolder, position: Int) {


        val photo = photos[position]
        holder.name.text = photo.user.name
        holder.author.text = photo.user.username

        Glide.with(holder.image.context)
            .load(photo.urls.raw)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            currentPhoto = photo
            onClick()
        }
    }

    //@TODO Can be replaced with bundle image link and then iterate through photos list or put data
    //@TODO object (but if object is too big - get problems and lost productivity, so I decided to like that)
    companion object {
        var currentPhoto: Photo? = null
    }

}

