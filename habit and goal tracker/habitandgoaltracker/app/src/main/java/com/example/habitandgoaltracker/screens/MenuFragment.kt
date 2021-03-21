package com.example.habitandgoaltracker.screens

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.habitandgoaltracker.R
import com.example.habitandgoaltracker.adapters.CategoryAdapter
import com.example.habitandgoaltracker.database.entities.Category
import com.example.habitandgoaltracker.databinding.FragmentMenuBinding
import com.example.habitandgoaltracker.dialog.CategoryDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList

class MenuFragment : Fragment() {

    private lateinit var viewModel: MenuViewModel

    private lateinit var binding: FragmentMenuBinding

    private lateinit var fab: FloatingActionButton

    override fun onCreateView(

        //"pretvaranje" XML datoteke u odgovarajući android.view.View ...

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)
        fab = binding.createCategory

        //spajanje baze i view modela

        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)

        /*Uz pomoć CategoryAdaptera i GRidLayoutManagera, u realtimeu imamo prikaz glavnom menija odnosno
        svih kategorija aktivnosti koje imamo i slično.
        */
        viewModel.categories.observe(viewLifecycleOwner, Observer { newCategories ->
            val adapter = CategoryAdapter(newCategories as ArrayList<Category>)
            val manager = GridLayoutManager(activity, 4)
            binding.categoryList.layoutManager = manager
            binding.categoryList.adapter = adapter

        })

        fab.setOnClickListener {
            val dialog = CategoryDialog(viewModel)

            // Uređaj koristi veliki layout, pa opet prikazujemo fragment kao dialog

            activity?.supportFragmentManager?.let { it1 -> dialog.show(it1, "dialog") }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    // navigacija na dodatni menu koji je u našem slučaju "Details"

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    //navigacija na novu kategoriju pomocu NavControllera

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item1 -> {
                requireView().findNavController()
                    .navigate(R.id.action_homeFragment_to_createCategoryFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
