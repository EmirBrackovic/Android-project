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

class FragmentActivityInstanceAmountAdapter(private val instanceList: List<ActivityInstance>, private val unitOfMeasure: String) :
    RecyclerView.Adapter<FragmentActivityInstanceAmountAdapter.ActivityViewHolder>() {

    override fun onCreateViewHolder(

        parent: ViewGroup,

        viewType: Int

    ): ActivityViewHolder {

        Log.d("l", viewType.toString())

        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.amount_instance, parent, false)
        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ActivityViewHolder,
        position: Int
    ) {
        val instance = instanceList[position]

        val createdAtText = DateFormat.getDateTimeInstance().format(Date(instance.createdAt)).toString()

        holder.amount.text = "Consumed: " + instance.value + " " + unitOfMeasure

        holder.createdAt.text = "Created at: " + createdAtText
    }

    override fun getItemCount(): Int {
        return instanceList.size
    }

    class ActivityViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var amount: TextView = itemView.findViewById(R.id.amount_TV)
        var createdAt: TextView = itemView.findViewById(R.id.added_at_TV)
    }

}