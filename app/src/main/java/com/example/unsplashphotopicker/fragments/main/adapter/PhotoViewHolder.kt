package com.example.unsplashphotopicker.fragments.main.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashphotopicker.R

class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name : TextView = itemView.findViewById(R.id.name)
    val image : ImageView = itemView.findViewById(R.id.image)
    val author : TextView = itemView.findViewById(R.id.author)
}