package com.example.habitandgoaltracker.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "category_id")
    var categoryId: Long = 0L,

    @ColumnInfo(name = "category_name")
    var name: String,

    @ColumnInfo(name = "category_type")
    var type: String,
                                                                    
    @ColumnInfo(name = "measure_unit")
    var unitOfMeasure: String?,

    @ColumnInfo(name = "has_properties")
    var hasProperties: Boolean,

    @ColumnInfo(name = "increment")
    var increment: Double?,

    @ColumnInfo(name = "created_at")
    var createdAtMillis: Long
)