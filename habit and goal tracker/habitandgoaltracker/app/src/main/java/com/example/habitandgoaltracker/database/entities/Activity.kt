package com.example.habitandgoaltracker.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity")
data class Activity(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "activity_id")
    var activityId: Long,

    @ColumnInfo(name = "activity_name")
    var name: String,

    @ColumnInfo(name = "category_id")
    var categoryId: Long,

    @ColumnInfo(name = "created_at")
    var createdAtMillis: Long = System.currentTimeMillis()

)

