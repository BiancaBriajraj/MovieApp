package com.example.movieapp.model.servies

import com.example.movieapp.model.MovieFullDetails
import com.example.movieapp.model.MovieSummDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieSummDetailsApiService {

    /*
    Movie title search
    https://api.themoviedb.org/3/search/movie?api_key=092ef080e5f1d57b6101f71603911376&query=Movie+Title
     */
    @GET("search/movie?")
    fun getSearchedMovieInfo( @Query("api_key")api_key: String ,@Query("query")query: String ): Call<MovieSummDetails>

    /*
    Discover all movies
    https://api.themoviedb.org/3/discover/movie?api_key=092ef080e5f1d57b6101f71603911376&language=en-US&sort_by=original_title.asc&include_adult=true&include_video=true&page=500&with_watch_monetization_types=flatrate
    https://api.themoviedb.org/3/discover/movie?api_key=092ef080e5f1d57b6101f71603911376&language=en-US&sort_by=original_title.asc&include_adult=true&page=80
     */
    @GET("discover/movie?api_key={api_key}&language=en-US&sort_by=original_title.asc&include_adult={adult}&include_video=true&page={pageNumber}")
    fun getMovies( @Path("api_key")api: String ,@Path("adult")value: Boolean,@Path("pageNumber")pageNo: Int  ): Call<MovieSummDetails>

}