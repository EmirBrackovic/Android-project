package com.example.habitandgoaltracker.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.habitandgoaltracker.R
import com.example.habitandgoaltracker.screens.ActivityViewModel


class ActivityDialog(

    //Dialog u okviru neke aktivnosti koji daje mogućnost da se doda neki novi događaj, hrana, rutina i slično.

    private val viewModel: ActivityViewModel,

    private val categoryType: String

) : DialogFragment() {

    private lateinit var dialog: View

    private lateinit var editActivityName: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder =
            AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater

        dialog = inflater.inflate(R.layout.activity_dialog, null)

        editActivityName = dialog.findViewById(R.id.name_of_activity_ET)

        builder.setView(dialog)
            .setTitle("New Activity")
            .setPositiveButton(
                "Add"
            ) { _, _ ->
                addActivity()
            }
            .setNegativeButton(
                "Cancel"
            ) { _, _ ->
                dismiss()
            }

        return builder.create()
    }

    private fun addActivity() {
        when (categoryType) {
            "incremental" -> {
                viewModel.insertIncrementalActivity(
                    editActivityName.text.toString()
                )
            }
            else -> {
                viewModel.insertActivity(
                    editActivityName.text.toString()
                )
            }
        }
        dismiss()
    }
}
