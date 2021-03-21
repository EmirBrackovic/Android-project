package com.example.habitandgoaltracker.screens

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.habitandgoaltracker.database.HabitTrackerDB
import com.example.habitandgoaltracker.database.entities.*

class ActivityInstanceViewModel(

    application: Application,

    //Id naše aktivnosti

    val activityId: Long,

    //Id kategorije

    val categoryId: Long,

    //Jedinica za mjerenje podataka

    val unitOfMeasure: String


) : ViewModel() {

    //Dobijanje podataka iz DB (Baze podataka)

    //Funkcije kreirane u DB i iskorištene ovdje za ubacivanje i refreshovanje podataka

    private val db = HabitTrackerDB.getInstance(application).habitTrackerDAO()
    var instances = db.getInstancesByActivityId(activityId)

    init {

        Log.i("v", instances.toString())

    }

    fun insertActivityInstance() {
        db.insertActivityInstance(
            ActivityInstance(0, activityId, null)
        )
    }

    fun updateActivityInstance(activityInstance: ActivityInstance) {
        db.updateInstance(activityInstance)
    }
}