package com.example.habitandgoaltracker.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.habitandgoaltracker.R
import com.example.habitandgoaltracker.database.entities.FullActivity
import com.example.habitandgoaltracker.screens.ActivityFragmentDirections
import com.example.habitandgoaltracker.screens.ActivityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AmountAdapter(activityList: List<FullActivity>, private val viewModel: ActivityViewModel, private val categoryId: Long, private val categoryType: String, private val unitOfMeasure: String) :

    /*Adapter napravljen za količinske aktivnosti, odnosno kategorije koje  ne mjere vremenski interval, nego količinu koju korisnik
    unosi u toku dana */
    RecyclerView.Adapter<AmountAdapter.ActivityViewHolder>() {

    //lista kativnosti koje su u okviru kategorije

    val activityList: List<FullActivity> = activityList

    override fun onCreateViewHolder(

        parent: ViewGroup,

        viewType: Int

    ): ActivityViewHolder {
        Log.d("l", viewType.toString())
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.amount_item, parent, false)

        //konvertovanje XML fajla u odgovarajuci View objekat

        return ActivityViewHolder(view)
    }

    /* Poziva ga RecyclerView da pokaze podatke na odredjenoj poziciji. Ova metoda updateuje sadržaj
    RecyclerView.ViewHolder.itemView da bi pokazao elemenat na datoj poziciji
     */

    override fun onBindViewHolder(
        holder: ActivityViewHolder,
        position: Int
    ) {
        val activity: FullActivity = activityList[position]
        holder.activityName.text = activity.activity.name + " " + "you used in " +unitOfMeasure +":"
        holder.card.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                ActivityFragmentDirections.actionActivityFragmentToActivityInstanceFragment(
                    activity.activity.activityId, categoryId, unitOfMeasure, categoryType
                )
            )
        )

        // Fab za dodavanje količine unešene u datoj aktivnosti

        holder.addAmountFab.setOnClickListener {
            val value = holder.editTextNumberDecimal.text.toString()
            if (value == "") {
                return@setOnClickListener
            }
            viewModel.insertActivityInstance(activity.activity.activityId, value.toDouble())
        }

    }

    override fun getItemCount(): Int {
        return activityList.size
    }

    class ActivityViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var activityName: TextView = itemView.findViewById(R.id.amountitem)
        var card: CardView = itemView.findViewById(R.id.amount_card)
        var addAmountFab: FloatingActionButton = itemView.findViewById(R.id.add_amount)
        var editTextNumberDecimal: EditText = itemView.findViewById(R.id.editTextNumberDecimal)
    }

}