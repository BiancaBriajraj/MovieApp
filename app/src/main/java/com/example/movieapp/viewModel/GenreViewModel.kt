package com.example.movieapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.models.GenresInfo
import com.example.movieapp.model.servies.GenresApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GenreViewModel : ViewModel() {

    private val BASE_URL = "https://api.themoviedb.org/3/genre/movie/"
    private val API_KEY = "092ef080e5f1d57b6101f71603911376"
    private val _listOfGenres = MutableLiveData<GenresInfo>()
    val listOfGenres: LiveData<GenresInfo> get() = _listOfGenres

    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()

    fun getInfo() {
        retrieveList()
    }

    private fun retrieveList() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        loading.value = true

        val apiService = retrofit.create(GenresApiService::class.java)
        val mCall: Call<GenresInfo> = apiService.getGenres(API_KEY)
        mCall.enqueue(object : Callback<GenresInfo> {
            override fun onResponse(call: Call<GenresInfo>, response: Response<GenresInfo>) {
                if (response.isSuccessful) {
                    _listOfGenres.value = response.body()!!
                    loading.value = false
                } else {
                    loading.value = false
                }
            }
            override fun onFailure(call: Call<GenresInfo>, t: Throwable) {
                loading.value = false
                error.value = true
            }
        })
    }
}