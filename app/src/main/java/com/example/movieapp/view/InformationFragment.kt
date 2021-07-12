package com.example.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
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

            viewModel.listOfFullMovieDetails.observe(viewLifecycleOwner, { list->
                binding.tester.text = list.overview
            })


        }

    }

}