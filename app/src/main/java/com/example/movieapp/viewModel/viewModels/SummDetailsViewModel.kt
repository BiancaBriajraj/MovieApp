package com.example.movieapp.viewModel.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.models.MovieSummDetails
import com.example.movieapp.model.servies.MovieSummDetailsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SummDetailsViewModel : ViewModel() {
    private val BASE_URL = "https://api.themoviedb.org/3/"
    private val API_KEY = "092ef080e5f1d57b6101f71603911376"
    private val _listSearched = MutableLiveData<MovieSummDetails>()
    val listSearch: LiveData<MovieSummDetails> get() = _listSearched
    private val _listAllMovies = MutableLiveData<MovieSummDetails>()
    val listAllMovies: LiveData<MovieSummDetails> get() = _listAllMovies
    val moviesLoading = MutableLiveData<Boolean>()
    val searchLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    fun getSearchedMovieDetails(movieName: String) {
        findMovie(movieName)
    }

    fun getAdult(adultIncluded: Boolean) {
        sortMovies(adultIncluded)
    }

    private fun findMovie(name: String) {
        searchLoading.value = true
        val apiService = retrofit.create(MovieSummDetailsApiService::class.java)
        val mCall: Call<MovieSummDetails> = apiService.getSearchedMovieInfo(API_KEY, name)
        mCall.enqueue(object : Callback<MovieSummDetails> {
            override fun onResponse(call: Call<MovieSummDetails>, response: Response<MovieSummDetails>) {
                if (response.isSuccessful) {
                    _listSearched.value = response.body()!!
                    searchLoading.value = false
                    if (response.body()!!.results.isEmpty()) {
                        error.value = true
                    }
                } else {
                    searchLoading.value = false
                }

            }

            override fun onFailure(call: Call<MovieSummDetails>, t: Throwable) {
                searchLoading.value = false
                error.value = true
            }
        })
    }

    private fun sortMovies(adultIncluded: Boolean) {
        moviesLoading.value = true
        val apiService = retrofit.create(MovieSummDetailsApiService::class.java)
        for (i in 1..10) {
            val mCall: Call<MovieSummDetails> = apiService.getMovies(API_KEY, adultIncluded, i)
            mCall.enqueue(object : Callback<MovieSummDetails> {
                override fun onResponse(call: Call<MovieSummDetails>, response: Response<MovieSummDetails>) {
                    if (response.isSuccessful) {
                        _listAllMovies.value = response.body()!!
                    } else {
                        moviesLoading.value = false
                        return
                    }
                }

                override fun onFailure(call: Call<MovieSummDetails>, t: Throwable) {
                    moviesLoading.value = false
                    error.value = true
                    return
                }
            })
            moviesLoading.value = false
        }
    }
}