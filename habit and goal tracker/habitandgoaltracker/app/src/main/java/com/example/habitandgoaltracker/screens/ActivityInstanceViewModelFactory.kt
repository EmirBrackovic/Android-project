package com.example.habitandgoaltracker.screens


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ActivityInstanceViewModelFactory(

    private val appContext: Application,

    //ID naše aktivnosti

    private val activityId: Long,

    //ID naše kategorije

    private val categoryId: Long,

    //Jedinica za mjerenje u aktivnosti

    private val unitOfMeasure: String

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActivityInstanceViewModel::class.java)) {
            return ActivityInstanceViewModel(appContext, activityId, categoryId, unitOfMeasure) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}