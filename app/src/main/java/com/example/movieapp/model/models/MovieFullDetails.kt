package com.example.movieapp.model

import com.example.movieapp.model.models.Genres
import com.google.gson.annotations.SerializedName

data class MovieFullDetails(
    val adult:Boolean,
    @SerializedName("backdrop_path")
    val backdropPath :String,
    @SerializedName("belongs_to_collection")
    val collection: Collection,
    val genres: ArrayList<Genres>,
    val homepage: String?,
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String?,
    @SerializedName("original_language")
    val orgLanguage:String,
    @SerializedName("original_title")
    val orgTitleString:String,
    val overview: String?,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("production_companies")
    val productionCompanies: ArrayList<ProductionCompanies>,
    @SerializedName("production_countries")
    val productionCountries: ArrayList<ProductionCountries>,
    @SerializedName("release_date")
    val releaseDate :String,
    val revenue:Int,
    val runtimes: Int?,
    @SerializedName("spoken_languages")
    val spokenLanguages:ArrayList<SpokenLanguages>,
    val status : String,
    val tagline:String?,
    val title:String,
    val video:Boolean,
    @SerializedName("vote_average")
    val voteAvg : Double,
    @SerializedName("vote_count")
    val voteCount:Int

)

data class Collection(
    val id: Int,
    val name: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String
)

data class ProductionCompanies(
    val name: String,
    val id: Int,
    @SerializedName("logo_path")
    val logoPath:String?,
    @SerializedName("origin_country")
    val originCountry :String
)

data class ProductionCountries(
    @SerializedName("iso_3166_1")
    val iso :String,
    val name: String
)
data class SpokenLanguages(
    @SerializedName("iso_636_1")
    val iso :String,
    val name: String
)

