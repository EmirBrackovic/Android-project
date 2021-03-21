package com.example.habitandgoaltracker.screens

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.habitandgoaltracker.database.HabitTrackerDB
import com.example.habitandgoaltracker.database.entities.*

class ActivityViewModel(private val application: Application, private val categoryId: Long) : ViewModel() {

    //dobijanje podataka iz baze podataka

    private val db = HabitTrackerDB.getInstance(application).habitTrackerDAO()

    // dobijanje liste aktivnosti po Id-u

    var activities = db.getActivitiesByCategoryId(categoryId)

    init {

        Log.i("v", activities.toString())

    }

    //Funkcije kreirane i u Bazi podataka za unošenje podataka, dodavanje aktivnosti, update-ovanja podataka, povećavanja i smanjivanja vrijednosti i slično.

    fun insertIncrementalActivity(activityName: String) {
        val insertedId = db.insertActivity(
            Activity(0, activityName, categoryId)
        )
        db.insertActivityInstance(ActivityInstance(0,insertedId,.0))
    }

    fun insertActivity(activityName: String) {
        db.insertActivity(
            Activity(0, activityName, categoryId)
        )
    }

    fun insertActivityInstance(activityId: Long, value: Double? = null) {
        db.insertActivityInstance(
            ActivityInstance(0, activityId, value)
        )
    }

    fun updateActivityInstance(activityInstance: ActivityInstance) {
        db.updateInstance(activityInstance)
    }

    fun increaseValue (id : Long,activityId: Long, value: Double?) {
        if (value != null) {
            db.updateInstance(ActivityInstance(id, activityId ,value + 1))
        }
    }

    fun decreaseValue (id : Long,activityId: Long, value: Double?) {

        if (value != null) {
            db.updateInstance(ActivityInstance(id, activityId,value - 1))
        }
    }


}