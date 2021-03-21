package com.example.habitandgoaltracker.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity_instance")
data class ActivityInstance (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "instance_id")
    var instanceId: Long,

    @ColumnInfo(name = "activity_id")
    var activityId: Long,

    @ColumnInfo(name = "value")
    var value: Double?,

    @ColumnInfo(name = "start_time")
    var startTimeMillis: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time")
    var endTimeMillis: Long = startTimeMillis,

    @ColumnInfo(name = "created_at")
    var createdAt: Long = startTimeMillis
)