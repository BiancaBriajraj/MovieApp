package com.example.movieapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.models.MovieFullDetails
import com.example.movieapp.model.servies.MovieFullDetailsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FullDetailsViewModel : ViewModel() {

    private val BASE_URL = "https://api.themoviedb.org/3/"
    private val API_KEY = "092ef080e5f1d57b6101f71603911376"
    private val _listOfFullMovieDetails = MutableLiveData<MovieFullDetails>()
    val listOfFullMovieDetails: LiveData<MovieFullDetails> get() = _listOfFullMovieDetails
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()

    fun getInfo(retrievedID: Int) {
        retrieveList(retrievedID)
    }

    private fun retrieveList(movieID: Int) {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        loading.value = true
        val apiService = retrofit.create(MovieFullDetailsApiService::class.java)
        val mCall: Call<MovieFullDetails> = apiService.getFullMovieInfo(movieID, API_KEY)
        mCall.enqueue(object : Callback<MovieFullDetails> {
            override fun onResponse(call: Call<MovieFullDetails>, response: Response<MovieFullDetails>) {
                if (response.isSuccessful) {
                    _listOfFullMovieDetails.value = response.body()!!
                    loading.value = false
                } else {
                    loading.value = false
                }
            }

            override fun onFailure(call: Call<MovieFullDetails>, t: Throwable) {
                loading.value = false
                error.value = true
            }
        })
    }
}
