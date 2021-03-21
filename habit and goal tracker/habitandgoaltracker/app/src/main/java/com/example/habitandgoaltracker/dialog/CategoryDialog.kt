package com.example.habitandgoaltracker.dialog


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import com.example.habitandgoaltracker.R
import com.example.habitandgoaltracker.screens.MenuViewModel


class CategoryDialog(private val viewModel: MenuViewModel) : DialogFragment(), AdapterView.OnItemSelectedListener {

    //Dialog koji se pokreće u glavnom meniju koji omogućava dodavanje nove kategorije u našu aplikaciju

    private lateinit var dialog: View

    private lateinit var editActivityName: EditText

    private lateinit var selectedItem : String

    private  lateinit var typeofcategories: List<String>

    var spinner:Spinner? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder =
            AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater

        dialog = inflater.inflate(R.layout.category_dialog, null)
        spinner = dialog.findViewById(R.id.spinnerr)
        spinner!!.setOnItemSelectedListener(this)
        typeofcategories = listOf("incremental","time","amount")
        val aa =
            context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, typeofcategories) }

        if (aa != null) {
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spinner!!.setAdapter(aa)

        editActivityName = dialog.findViewById(R.id.nameOfCategory)

        builder.setView(dialog)
            .setTitle("New Activity")
            .setPositiveButton(
                "Add"
            ) { _, _ ->
                addCategory()
            }
            .setNegativeButton(
                "Cancel"
            ) { _, _ ->
                dismiss()
            }

        return builder.create()


    }

    private fun addCategory() {
        viewModel.insertCategory(
            editActivityName.text.toString(),
            selectedItem
        )
        dismiss()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedItem = typeofcategories[position]
    }
}
