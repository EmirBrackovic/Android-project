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
import com.example.habitandgoaltracker.adapters.AmountAdapter
import com.example.habitandgoaltracker.adapters.IncrementalAdapter
import com.example.habitandgoaltracker.adapters.TimeAdapter
import com.example.habitandgoaltracker.databinding.ActivityFragmentBinding
import com.example.habitandgoaltracker.dialog.ActivityDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivityFragment : Fragment() {

    companion object {
        fun newInstance() = ActivityFragment()
    }

    //Dugme na ekranu za dodavanje u okviru aktivnosti novih dogadjaja

    private lateinit var fab: FloatingActionButton

    private lateinit var appContext: Application

    private lateinit var viewModel: ActivityViewModel

    private lateinit var activityViewModelFactory: ActivityViewModelFactory

    private lateinit var binding: ActivityFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //spajanje podataka
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_fragment, container, false)
        fab = binding.createActivity

        // dobijanje sadrzaja aplikacije

        appContext = this.activity?.applicationContext as Application

        // dobijanje argumenata s prošlog fragmenta

        val args = ActivityFragmentArgs.fromBundle(requireArguments())

        // inicijalizacija view modela početnog (defaultnog) sa dodatnim argumentima : contex i category id

        activityViewModelFactory = ActivityViewModelFactory(appContext, args.id)

        viewModel = ViewModelProvider(this, activityViewModelFactory).get(ActivityViewModel::class.java)

        binding.lifecycleOwner = this

        // postavljanje "observe" za dobijanje "LiveData", ako se desi promjena u podacima, tj. automatsko refreshovanje podataka

        viewModel.activities.observe(viewLifecycleOwner, Observer { newActivities ->

            Log.i("v", newActivities.toString())

            // Provjera koji je tip kategorije i postavljanje odgovarajuće

            when (args.categoryType) {
                "time" -> binding.activityRecycleIncremental.also {
                    it.adapter = TimeAdapter(newActivities, viewModel)
                }
                "amount" -> binding.activityRecycleIncremental.also {
                    it.adapter = AmountAdapter(newActivities, viewModel, args.id, args.categoryType, args.unitOfMeasure)
                }
                else -> binding.activityRecycleIncremental.also {
                    it.adapter = IncrementalAdapter(newActivities, viewModel)
                }
            }
        })

        // ClickListener za FAB za otvaranje ActivityDialoga

        fab.setOnClickListener(View.OnClickListener {

            val dialog = ActivityDialog(viewModel, args.categoryType)

            //Android uređaj koristi veliki Layout, pa prikazujemo fragment kao dialog

            activity?.supportFragmentManager?.let { it1 -> dialog.show(it1, "dialog") }
            Log.i("fablistener", dialog.toString())
        })

        return binding.root
    }
}