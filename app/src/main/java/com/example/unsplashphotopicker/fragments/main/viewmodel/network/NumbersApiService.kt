package com.example.unsplashphotopicker.fragments.main.viewmodel.network

import com.example.unsplashphotopicker.fragments.main.viewmodel.models.Photo
import com.example.unsplashphotopicker.utils.Utils
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NumbersApiService {

    @GET("photos/?client_id=${Utils.Unsplash}")
    fun getGet(): Call<List<Photo>>

//    @GET("{user}")
//    suspend fun getNumbersFact(@Path("user") user: String): Response<String>
//
//    @GET(Utils.RandomNumberUserURL)
//    suspend fun getRandomNumbersFact(): Response<String>


    companion object {
        var retrofitService: NumbersApiService? = null


        fun getInstance() : NumbersApiService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Utils.BaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(NumbersApiService::class.java)
            }
            return retrofitService!!
        }

    }
}