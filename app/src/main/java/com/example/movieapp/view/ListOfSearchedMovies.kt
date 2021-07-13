package com.example.movieapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentListOfSearchedMoviesBinding
import com.example.movieapp.viewModel.SearchedMoviesAdapter
import com.example.movieapp.viewModel.SummDetailsViewModel
import kotlinx.android.synthetic.main.fragment_list_of_searched_movies.*

class ListOfSearchedMovies : Fragment() {

    private lateinit var binding: FragmentListOfSearchedMoviesBinding
    private lateinit var viewModelSummMovie: SummDetailsViewModel


    private val listAdapter= SearchedMoviesAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentListOfSearchedMoviesBinding.inflate(inflater,container,false)
        return  binding.root
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelSummMovie = ViewModelProvider(this).get(SummDetailsViewModel::class.java)

        arguments?.let {  it ->
            val userText = it["userInputs"].toString()
            binding.userInputText.text = userText
            viewModelSummMovie.getSearchedMovieDetails(userText)

            viewModelSummMovie.loading.observe(viewLifecycleOwner, { loading ->
                if(loading){
                    binding.apply {
                        progressBarSearchList.visibility = View.VISIBLE
                        searchedRecylerView.visibility = View.GONE
                    }} else{
                        progressBarSearchList.visibility = View.GONE
                    }
            })
            viewModelSummMovie.listSearch.observe(viewLifecycleOwner, { it1 ->
                listAdapter.update(it1.results)
                binding.searchedRecylerView.visibility = View.VISIBLE
        })


    binding.searchedRecylerView.apply {
        layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        adapter = listAdapter
    }


        }
    }
}