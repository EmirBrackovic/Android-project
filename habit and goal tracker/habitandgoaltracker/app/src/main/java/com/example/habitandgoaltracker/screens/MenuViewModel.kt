package com.example.habitandgoaltracker.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.habitandgoaltracker.database.HabitTrackerDB
import com.example.habitandgoaltracker.database.entities.*

class MenuViewModel(application: Application) : AndroidViewModel(application) {

    private val db = HabitTrackerDB.getInstance(application).habitTrackerDAO()
    var categories = db.getAllCategories()

    //spajanje sa bazom i pozivanje funkcije iz baze pomoću koje prikazujemo kategoriju

    fun insertCategory(name: String, typec: String) {
        db.insertCategory(Category(0, name, typec, null, false, null, System.currentTimeMillis()))
    }



}