package com.example.unsplashphotopicker.fragments.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unsplashphotopicker.fragments.main.viewmodel.models.Photo
import com.example.unsplashphotopicker.fragments.main.viewmodel.network.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    private val _photos: MutableLiveData<List<Photo>> = MutableLiveData()
    val photos get() = _photos



    fun getPhotos() {
        val response = mainRepository.getPhotos()
        //@TODO Can be replaced with ResponseBody + coroutines, but wasn't required

        response.enqueue(object: Callback<List<Photo>>{
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                Log.i("photo", "onResponse = ${response.body()}")
                _photos.value = response.body()
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.i("photo", "onFailure = ${t.cause} and = ${t.message}")
            }
        })


    }


}