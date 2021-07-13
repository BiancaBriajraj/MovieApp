package com.example.movieapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentListOfMoviesBinding
import com.example.movieapp.viewModel.ListOfMoviesByGenreAdapter
import com.example.movieapp.viewModel.SummDetailsViewModel

class ListOfMoviesFragment : Fragment() {
    private lateinit var binding: FragmentListOfMoviesBinding
    private lateinit var viewModelSummMovie: SummDetailsViewModel


    private val listAdapter= ListOfMoviesByGenreAdapter(arrayListOf(),0)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListOfMoviesBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelSummMovie = ViewModelProvider(this).get(SummDetailsViewModel::class.java)
        viewModelSummMovie.getAdult(true)

        arguments?.let { it ->
            val genreCode = it["genreId"]

            binding.listCatName.text = it["genreName"].toString()
                viewModelSummMovie.listAllMovies.observe(viewLifecycleOwner, { list->
                    listAdapter.update(list.results, genreCode as Int)
                })

            binding.listOfMoviesbyGenres.apply {
                layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
                adapter = listAdapter
            }
        }
    }}


