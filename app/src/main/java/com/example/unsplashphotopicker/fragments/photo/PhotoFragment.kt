package com.example.unsplashphotopicker.fragments.photo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.unsplashphotopicker.R
import com.example.unsplashphotopicker.databinding.FragmentPhotoBinding
import com.example.unsplashphotopicker.fragments.main.adapter.MainPhotosAdapter
import com.example.unsplashphotopicker.fragments.main.viewmodel.models.Photo

class PhotoFragment : Fragment() {
    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!
    private lateinit var photo: Photo


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPhotoBinding.bind(view)
        Log.i("photo", "MainPhotosAdapter photo = ${MainPhotosAdapter.currentPhoto}")
        if (MainPhotosAdapter.currentPhoto != null) {
            photo = MainPhotosAdapter.currentPhoto!!
            binding.photoAuthor.text = photo.user.username

            Glide.with(binding.photoImage.context)
                .load(photo.urls.raw)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.photoImage)

            binding.photoName.text = photo.user.name
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}