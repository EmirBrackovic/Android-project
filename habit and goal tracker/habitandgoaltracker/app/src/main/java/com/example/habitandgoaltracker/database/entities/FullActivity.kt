package com.example.habitandgoaltracker.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class FullActivity (
    @Embedded
    val activity: Activity,
    @Relation(
        parentColumn = "activity_id",
        entityColumn = "activity_id"
    )
    val activityInstances: List<ActivityInstance>
)
