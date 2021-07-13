package com.example.movieapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentMainScreenBinding
import com.example.movieapp.viewModel.CategoryAdapter
import com.example.movieapp.viewModel.GenreViewModel
import com.example.movieapp.viewModel.SummDetailsViewModel


class MainScreen : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var viewModelGenre: GenreViewModel
    private lateinit var viewModelSummMovie: SummDetailsViewModel


    private val listAdapter=CategoryAdapter(arrayListOf(), arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentMainScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelGenre = ViewModelProvider(this).get(GenreViewModel::class.java)
        viewModelGenre.getInfo()
        viewModelSummMovie = ViewModelProvider(this).get(SummDetailsViewModel::class.java)
        viewModelSummMovie.getAdult(true)

        viewModelGenre.listOfGenres.observe(viewLifecycleOwner, { list ->
            viewModelSummMovie.listAllMovies.observe(viewLifecycleOwner, {
                viewModelSummMovie.listAllMovies.value?.let { it1 -> listAdapter.update(list, it1.results) }
                binding.categoryRecyclerView.visibility = View.VISIBLE
            })
        })

        binding.categoryRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
            adapter = listAdapter
        }

        binding.goButton.setOnClickListener {
            val userInput = binding.editTextTextPersonName.text.toString()
            Navigation.findNavController(it).navigate(MainScreenDirections.actionMainScreenToListOfSearchedMovies2(userInput))
        }
    }
}