package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieSummDetails(
    val page: Int,
    val results: ArrayList<Results>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
)

data class Results(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genre_ids")
    val genresIds: ArrayList<Int>,
    val id: Int,
    @SerializedName("original_language")
    val orgLanguage: String,
    @SerializedName("original_title")
    val orgTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAvg: Double,
    @SerializedName("vote_count")
    val voteCount: Int

)
