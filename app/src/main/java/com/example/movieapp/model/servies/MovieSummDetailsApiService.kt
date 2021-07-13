package com.example.movieapp.model.servies

import com.example.movieapp.model.models.MovieSummDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieSummDetailsApiService {

    /*
    Movie title search
    https://api.themoviedb.org/3/search/movie?api_key=092ef080e5f1d57b6101f71603911376&query=Movie+Title
     */
    @GET("search/movie?")
    fun getSearchedMovieInfo(@Query("api_key") api_key: String, @Query("query") query: String): Call<MovieSummDetails>

    /*
    Discover all movies
     https://api.themoviedb.org/3/discover/movie?api_key=092ef080e5f1d57b6101f71603911376&include_adult=true&page=30
     */
    @GET("discover/movie?")
    fun getMovies(@Query("api_key") api_key: String, @Query("include_adult") adults: Boolean, @Query("page") page: Int): Call<MovieSummDetails>

}