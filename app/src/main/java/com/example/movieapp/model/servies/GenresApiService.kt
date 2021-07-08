package com.example.movieapp.model.servies

import com.example.movieapp.model.models.Genres
import com.example.movieapp.model.models.GenresInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GenresApiService {

    //https://api.themoviedb.org/3/genre/movie/list?api_key=092ef080e5f1d57b6101f71603911376&language=en-US
    @GET("list?api_key=092ef080e5f1d57b6101f71603911376&language=en-US")
    fun getGenres(@Query("api_key") apiKey: String, @Query("language") type: String = "en-US"): Call<GenresInfo>

}