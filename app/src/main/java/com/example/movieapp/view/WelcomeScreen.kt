package com.example.movieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.movieapp.databinding.FragmentWelcomeScreenBinding

class WelcomeScreen : Fragment() {

    private lateinit var binding: FragmentWelcomeScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var kidsOnlyVaule = true
        binding.kidsOnlySwitch.setOnCheckedChangeListener { _, isChecked ->
            kidsOnlyVaule = !isChecked
        }

        binding.enterButton.setOnClickListener {
            Navigation.findNavController(it).navigate(WelcomeScreenDirections.actionWelcomeScreenToMainScreen(kidsOnlyVaule))
        }
    }


}