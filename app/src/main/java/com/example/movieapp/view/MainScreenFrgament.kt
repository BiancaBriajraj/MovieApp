package com.example.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentListOfMoviesBinding
import com.example.movieapp.databinding.FragmentMainScreenBinding
import com.example.movieapp.viewModel.SummDetailsViewModel
import kotlinx.android.synthetic.main.fragment_main_screen.*


class MainScreen : Fragment() {

private lateinit var binding: FragmentMainScreenBinding
private lateinit var viewModel: SummDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentMainScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SummDetailsViewModel::class.java)
        viewModel.getSearchedMovieDetails("Hulk")

        viewModel.listSearch.observe(viewLifecycleOwner, { list ->
               Glide.with(this)
                   .load("https://image.tmdb.org/t/p/w500/${list.results[0].posterPath}")
                   .into(imageView8)

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/${list.results[1].posterPath}")
                .into(imageView9)
        })





    }

}