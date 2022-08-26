package com.example.unsplashphotopicker.fragments.main.viewmodel.network

class MainRepository(private val numbersApiService: NumbersApiService) {
    fun getPhotos() = numbersApiService.getGet()
}