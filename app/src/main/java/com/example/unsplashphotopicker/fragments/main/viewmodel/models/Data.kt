package com.example.unsplashphotopicker.fragments.main.viewmodel.models

import com.google.gson.annotations.SerializedName

data class Photo(val urls: Url, val user: User)

data class Url(val raw: String)

data class User(val username: String, val name: String)