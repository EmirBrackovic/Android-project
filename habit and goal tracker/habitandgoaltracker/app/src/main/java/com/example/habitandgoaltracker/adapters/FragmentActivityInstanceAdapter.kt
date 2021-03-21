package com.example.habitandgoaltracker.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habitandgoaltracker.R
import com.example.habitandgoaltracker.database.entities.ActivityInstance
import java.text.DateFormat
import java.util.*


// Adapter za FragmentActivityInstancu  u okviru kojeg pokazujemo  detaljne podatke o nekoj aktivnosti u okviru kategorije


class FragmentActivityInstanceAdapter(private val instanceList: List<ActivityInstance>) :
    RecyclerView.Adapter<FragmentActivityInstanceAdapter.ActivityViewHolder>() {

    override fun onCreateViewHolder(

        parent: ViewGroup,

        viewType: Int

    ): ActivityViewHolder {
        Log.d("l", viewType.toString())
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.time_instance, parent, false)
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
        val instance = instanceList[position]

        //vrijeme kada je aktivnost pocela

        val startTimeText = DateFormat.getDateTimeInstance().format(Date(instance.startTimeMillis)).toString()

        //vrijeme kada je aktivnost zavrsila

        val endTimeText = DateFormat.getDateTimeInstance().format(Date(instance.endTimeMillis)).toString()

        //tekst koji vidimo na ekranu

        holder.startTime.text = "Start: " + startTimeText

        holder.endTime.text = "Stop: " + endTimeText
    }

    override fun getItemCount(): Int {
        return instanceList.size
    }

    class ActivityViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var startTime: TextView = itemView.findViewById(R.id.start_time)
        var endTime: TextView = itemView.findViewById(R.id.end_time)
    }

}