package com.example.movieapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentListOfMoviesBinding
import com.example.movieapp.viewModel.adapters.ListOfMoviesByGenreAdapter
import com.example.movieapp.viewModel.viewModels.SummDetailsViewModel

class ListOfMoviesFragment : Fragment() {
    private lateinit var binding: FragmentListOfMoviesBinding
    private lateinit var viewModelSummMovie: SummDetailsViewModel
    private val listAdapter = ListOfMoviesByGenreAdapter(arrayListOf(), 0)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListOfMoviesBinding.inflate(inflater, container, false)
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
            viewModelSummMovie.moviesLoading.observe(viewLifecycleOwner, { loading ->
                if (loading) {
                    binding.apply {
                        progressBar2.visibility = View.VISIBLE
                        listOfMoviesbyGenres.visibility = View.GONE
                    }
                } else {
                    binding.progressBar2.visibility = View.GONE
                }
            })
            viewModelSummMovie.listAllMovies.observe(viewLifecycleOwner, { list ->
                listAdapter.update(list.results, genreCode as Int)
                binding.listOfMoviesbyGenres.visibility = View.VISIBLE
            })
            binding.listOfMoviesbyGenres.apply {
                layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
                adapter = listAdapter
            }
        }
        viewModelSummMovie.error.observe(viewLifecycleOwner, { errorMovieList ->
            if (errorMovieList) {
                binding.apply {
                    errorListOfMovies.visibility = View.VISIBLE
                    listOfMoviesbyGenres.visibility = View.GONE
                    Toast.makeText(context, "Error has occurred. Please try again", Toast.LENGTH_SHORT).show()
                }
            } else {
                binding.errorListOfMovies.visibility = View.GONE
            }
        })
    }
}


