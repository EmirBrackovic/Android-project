package com.example.habitandgoaltracker.screens

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ActivityViewModelFactory(
    private val appContext: Application,
    private val categoryId: Long
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActivityViewModel::class.java)) {
            return ActivityViewModel(appContext, categoryId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}