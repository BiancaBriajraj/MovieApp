package com.example.movieapp.model.servies

import com.example.movieapp.model.MovieFullDetails
import com.example.movieapp.model.models.Genres
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieFullDetailsApiService {

    //https://api.themoviedb.org/3/movie/550?api_key=092ef080e5f1d57b6101f71603911376&language=en-US
    @GET("movie/{id}?")
    fun getFullMovieInfo(@Path("id")type: Int, @Query("api_key")api_key: String ): Call<MovieFullDetails>
}