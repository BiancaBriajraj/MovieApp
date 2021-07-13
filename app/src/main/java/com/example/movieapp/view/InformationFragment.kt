package com.example.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentInformationBinding
import com.example.movieapp.viewModel.FullDetailsViewModel

class InformationFragment : Fragment() {

    private lateinit var binding: FragmentInformationBinding
    private lateinit var viewModel: FullDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FullDetailsViewModel::class.java)

        arguments?.let {
           val movieID = it["movieID"]

            viewModel.getInfo(movieID as Int)

            viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
                if(loading)  binding.progressBar1.visibility = View.VISIBLE
                else binding.progressBar1.visibility = View.GONE
            })
            viewModel.listOfFullMovieDetails.observe(viewLifecycleOwner, { list->
                binding.apply {
                    infoMovieName.text = list.title
                    Glide.with(this@InformationFragment)
                        .load("https://image.tmdb.org/t/p/w500/${list.posterPath}")
                        .into(infoMovieImage)
                    infoMovieYear.text = list.releaseDate
                    infoVoteAverage.text = getString(R.string.vote_average,list.voteAvg.toString())
                    infoLanguage.text = getString(R.string.language, list.orgLanguage)
                    infoProductionCompany.text = getString(R.string.production_company," ")
                    list.productionCompanies.forEach { prod ->
                        infoProductionCompany.append(prod.name)
                    }
                    infoMovieOverview.text = list.overview

                }

            })


        }

    }

}