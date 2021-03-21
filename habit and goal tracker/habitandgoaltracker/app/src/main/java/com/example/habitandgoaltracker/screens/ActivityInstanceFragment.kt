package com.example.habitandgoaltracker.screens

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.habitandgoaltracker.R
import com.example.habitandgoaltracker.adapters.FragmentActivityInstanceAdapter
import com.example.habitandgoaltracker.adapters.FragmentActivityInstanceAmountAdapter
import com.example.habitandgoaltracker.databinding.FragmentActivityInstanceBinding

class ActivityInstanceFragment : Fragment() {

    private lateinit var appContext: Application

    private lateinit var viewModel: ActivityInstanceViewModel

    private lateinit var activityInstanceViewModelFactory: ActivityInstanceViewModelFactory

    private lateinit var binding: FragmentActivityInstanceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_activity_instance, container, false)

        // dobijanje sadrzaja aplikacije

        appContext = this.activity?.applicationContext as Application

        // dobijanje argumenata s prošlog fragmenta

        val args = ActivityInstanceFragmentArgs.fromBundle(requireArguments())

        // inicijalizacija view modela početnog (defaultnog) sa dodatnim argumentima : contex i category id

        activityInstanceViewModelFactory = ActivityInstanceViewModelFactory(appContext, args.activityId, args.categoryId, args.unitOfMeasure)

        Log.i("v", args.toString())

        viewModel = ViewModelProvider(this, activityInstanceViewModelFactory).get(ActivityInstanceViewModel::class.java)

        binding.lifecycleOwner = this

        // postavljanje "observe" za dobijanje "LiveData", ako se desi promjena u podacima, tj. automatsko refreshovanje podataka

        viewModel.instances.observe(viewLifecycleOwner, Observer { newInstances ->
            Log.i("v", newInstances.toString())
            when (args.categoryType) {
                "time" ->
                    binding.activityInstanceRecycler.adapter = FragmentActivityInstanceAdapter(newInstances)
                else -> binding.activityInstanceRecycler.adapter = FragmentActivityInstanceAmountAdapter(newInstances, args.unitOfMeasure)
            }

        })

        return binding.root
    }
}