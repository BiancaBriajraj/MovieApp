package com.example.movieapp.model.models

data class Genres(
    val id: Int,
    val name: String
)

data class GenresInfo(val genres: ArrayList<Genres>)

