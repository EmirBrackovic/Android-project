package com.example.habitandgoaltracker.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.habitandgoaltracker.R
import com.example.habitandgoaltracker.database.entities.FullActivity
import com.example.habitandgoaltracker.screens.ActivityFragmentDirections
import com.example.habitandgoaltracker.screens.ActivityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

//Adapter za vremenske aktivnosti, tačnije mjerenje koliko je određena aaktivnost u ovkiru kategorije trajala

class TimeAdapter(
    private val activityList: List<FullActivity>,
    private val viewModel: ActivityViewModel
) :
    RecyclerView.Adapter<TimeAdapter.ActivityViewHolder>() {

    // U okviru Activity View Modela dobijemo unkcije kreirane i u Bazi podataka za unošenje podataka,
    // dodavanje aktivnosti, update-ovanja podataka, povećavanja i smanjivanja vrijednosti i slično.

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActivityViewHolder {
        Log.d("l", viewType.toString())
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.time_item, parent, false)
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
        holder.activityName.text = activity.activity.name

        holder.card.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                ActivityFragmentDirections.actionActivityFragmentToActivityInstanceFragment(
                    activity.activity.activityId, 0, "", "time"
                )
            )
        )

        //dugme za pokretanje mjerenja vremena

        holder.startButton.setOnClickListener {
            Log.i("v", "kliknut")

            val lastInstance =  if (activity.activityInstances.isEmpty()) null else activity.activityInstances[activity.activityInstances.size - 1]

            if (lastInstance != null && lastInstance.startTimeMillis == lastInstance.endTimeMillis) {
                return@setOnClickListener
            }

            viewModel.insertActivityInstance(activity.activity.activityId)

            //Notifikacija koju koristimo da javi korisniku da je počela aktivnost

            val toast = Toast.makeText(holder.activityName.context, "You started Activity, GOOD LUCK !", Toast.LENGTH_LONG)
            toast.show()
        }

        //dugme za zaustavljanje mjerača

        holder.stopButton.setOnClickListener {
            Log.i("v", "kliknut")

            if (activity.activityInstances.isEmpty()) {
                return@setOnClickListener
            }

            val lastInstance = activity.activityInstances[activity.activityInstances.size - 1]

            if(lastInstance.startTimeMillis != lastInstance.endTimeMillis) {
                return@setOnClickListener
            }
            lastInstance.endTimeMillis = System.currentTimeMillis()

            viewModel.updateActivityInstance(lastInstance)

            //poruka kada se zaustavi mjerač

            val toast = Toast.makeText(holder.activityName.context, "Activity stopped ! Nice job :) ", Toast.LENGTH_LONG)

            toast.show()
        }
    }

    override fun getItemCount(): Int {

        return activityList.size

    }

    class ActivityViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var activityName: TextView = itemView.findViewById(R.id.textAmount)

        var startButton: FloatingActionButton = itemView.findViewById(R.id.start)

        var stopButton: FloatingActionButton = itemView.findViewById(R.id.stop)

        var card: CardView = itemView.findViewById(R.id.time_card)
    }

}