package com.example.movieapp.viewModel

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.example.movieapp.R
import com.example.movieapp.model.Results
import com.example.movieapp.model.models.Genres
import com.example.movieapp.model.models.GenresInfo
import com.example.movieapp.view.MainScreenDirections


class CategoryAdapter(private val genreList:ArrayList<Genres>, private val moviePosters : ArrayList<Results>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

     private val viewPool: RecycledViewPool = RecycledViewPool()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val recyclerView : RecyclerView = itemView.findViewById(R.id.movieRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.category_item,parent,false)
        return ViewHolder(layout)
    }

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.findViewById<TextView>(R.id.categoryNameText).text = genreList[position].name
        holder.itemView.findViewById<TextView>(R.id.genreCode).text = genreList[position].id.toString()

        val childLayoutManager = LinearLayoutManager(holder.recyclerView.context, LinearLayout.HORIZONTAL, false)
       // childLayoutManager.initialPrefetchItemCount = 3
        holder.recyclerView.apply {
            layoutManager = childLayoutManager
           // addItemDecoration(resources.getDimensionPixelSize(R.dimen))
            adapter = ListOfMoviesMainScreenAdapter(moviePosters,genreList[position].id)

            setRecycledViewPool(viewPool)
        }

        holder.itemView.findViewById<Button>(R.id.listMovieBtn).setOnClickListener {
            Navigation.findNavController(it).navigate(MainScreenDirections.actionMainScreenToListOfMoviesFragment2(genreList[position].id,genreList[position].name))
        }


    }

    override fun getItemCount(): Int {
       return genreList.size
    }
    fun update(newList: GenresInfo,movie : ArrayList<Results>){
        genreList.clear()
        genreList.addAll(newList.genres)
        moviePosters.addAll(movie)
        notifyDataSetChanged()

    }
}