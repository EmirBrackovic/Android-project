package com.example.habitandgoaltracker.database

import com.example.habitandgoaltracker.database.entities.ActivityInstance
import com.example.habitandgoaltracker.database.entities.Category

class DBData {

    val categories: List<Category> = listOf(
        Category(1, "Medication", "amount", "mg", true, null, System.currentTimeMillis()),

        Category(2, "Workout", "time", null, true, null, System.currentTimeMillis()),

        Category(3, "Study", "time", null, true, null, System.currentTimeMillis()),

        Category(4, "Eat", "amount", "kcal", true, null, System.currentTimeMillis()),

        Category(5, "Sleep", "time", null, true, null, System.currentTimeMillis()))













    val activityInstances: List<ActivityInstance> = listOf(
        ActivityInstance(
            1, 1, null, System.currentTimeMillis() + 30000, System.currentTimeMillis() + 3629230
        )
    )
}
