package com.example.movieapp.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.model.models.Results
import com.example.movieapp.view.ListOfSearchedMoviesDirections

class SearchedMoviesAdapter(private val movieList: ArrayList<Results>) : RecyclerView.Adapter<SearchedMoviesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val media = "https://image.tmdb.org/t/p/w500/${movieList[position].posterPath}"
        val poster = holder.itemView.findViewById<ImageView>(R.id.moviePosterImage)
        Glide.with(holder.itemView)
            .load(media)
            .into(poster)
        poster.setOnClickListener {
            Navigation.findNavController(it).navigate(ListOfSearchedMoviesDirections.actionListOfSearchedMovies2ToInformationFragment2(movieList[position].id))
        }
    }
    override fun getItemCount(): Int {
        return movieList.size
    }

    fun update(newList: ArrayList<Results>) {
        movieList.clear()
        movieList.addAll(newList)
        notifyDataSetChanged()
    }
}