package com.example.habitandgoaltracker.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.habitandgoaltracker.R
import com.example.habitandgoaltracker.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    // Dodatne informacije o aplikaciji

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDetailsBinding>(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

}
