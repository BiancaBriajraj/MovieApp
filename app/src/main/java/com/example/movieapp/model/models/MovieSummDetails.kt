package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieSummDetails(
    val page:Int,
    val results :ArrayList<Results>,
    @SerializedName("total_results")
    val totalResults:Int,
    @SerializedName("total_pages")
    val totalPages: Int
)

data class Results(
    @SerializedName("poster_path")
    val posterPath: String,
    val adult:Boolean,
    val overview:String,
    @SerializedName("realease_date")
    val releaseDate:String,
    @SerializedName("genres_ids")
    val genresIds :ArrayList<Int>,
    @SerializedName("original_title")
    val orgTitle:String,
    @SerializedName("original_language")
    val orgLanguage:String,
    val title:String,
    @SerializedName("backdrop_path")
    val backdropPAth :String?,
    val popularity:Double,
    @SerializedName("vote_count")
    val voteCount :Int,
    val video:Boolean,
    @SerializedName("vote_average")
    val voteAvg: Double
)
