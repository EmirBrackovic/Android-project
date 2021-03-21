package com.example.habitandgoaltracker.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "property")
data class Property(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "property_id")
    var propertyId: Long = 0L,

    @ColumnInfo(name = "property_name")
    var name: String,

    @ColumnInfo(name = "category_id")
    var categoryId: String
)