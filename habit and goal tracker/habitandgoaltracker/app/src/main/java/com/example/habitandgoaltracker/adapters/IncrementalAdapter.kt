package com.example.habitandgoaltracker.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habitandgoaltracker.R
import com.example.habitandgoaltracker.database.entities.FullActivity
import com.example.habitandgoaltracker.screens.ActivityViewModel

class IncrementalAdapter(activityList: List<FullActivity>, viewholder: ActivityViewModel) :
    RecyclerView.Adapter<IncrementalAdapter.ActivityViewHolder>() {

    /*Adapter napravljen za količinske aktivnosti, odnosno kategorije koje  ne mjere vremenski interval, nego količinu koju korisnik
    unosi u toku dana */

    val activityList: List<FullActivity>

    //lista aktivnosti koje su u datoj kategoriji

    val viewholder: ActivityViewModel

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActivityViewHolder {
        Log.d("l", viewType.toString())
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.incremental_item, parent, false)
        return ActivityViewHolder(view)

        //konvertovanje XML fajla u odgovarajuci View objekat

    }

    /* Poziva ga RecyclerView da pokaze podatke na odredjenoj poziciji. Ova metoda updateuje sadržaj
    RecyclerView.ViewHolder.itemView da bi pokazao elemenat na datoj poziciji
     */

    override fun onBindViewHolder(
        holder: ActivityViewHolder,
        position: Int
    ) {
        val activity: FullActivity = activityList[position]
        Log.d("tag", activity.toString())

        holder.activityName.text =
            activity.activity.name + " " + activity.activityInstances[0].value + " times"

        //instumenti za povećavanje količine

        holder.buttonIncrease.setOnClickListener {
            viewholder.increaseValue(
                activity.activityInstances[0].instanceId,

                activity.activityInstances[0].activityId,

                activity.activityInstances[0].value
            )
        }

        //instumenti za smanjivanje količine

        holder.buttonDecrease.setOnClickListener {
            viewholder.decreaseValue(
                activity.activityInstances[0].instanceId,

                activity.activityInstances[0].activityId,

                activity.activityInstances[0].value
            )
        }

    }

    override fun getItemCount(): Int {
        return activityList.size
    }


    class ActivityViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var activityName: TextView

        var buttonIncrease: View

        var buttonDecrease: View

        init {
            activityName = itemView.findViewById(R.id.incrementalName)

            buttonIncrease = itemView.findViewById(R.id.increase)

            buttonDecrease = itemView.findViewById(R.id.decrease)

        }
    }

    init {

        this.activityList = activityList

        this.viewholder = viewholder

    }
}