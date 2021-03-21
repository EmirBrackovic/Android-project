package com.example.habitandgoaltracker.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.habitandgoaltracker.database.entities.*

@Dao
interface HabitTrackerDAO {
    @Query("SELECT * FROM category")
    fun getAllCategories(): LiveData<List<Category>>

    @Query("SELECT * FROM activity WHERE category_id = :id ORDER BY created_at")
    fun getActivitiesByCategoryId(id: Long): LiveData<List<FullActivity>>

    @Query("SELECT * FROM activity_instance WHERE activity_id = :id ORDER BY created_at")
    fun getInstancesByActivityId(id: Long): LiveData<List<ActivityInstance>>

    @Update
    fun updateActivity(activity: Activity)

    @Update
    fun updateInstance(instance: ActivityInstance)

    @Insert
    fun insertCategories(categories: List<Category>)

    @Insert
    fun insertCategory(category: Category)

    @Insert
    fun insertActivities(activities: List<Activity>)

    @Insert
    fun insertActivity(activity: Activity): Long

    @Insert
    fun insertActivityInstances(activityInstances: List<ActivityInstance>)

    @Insert
    fun insertActivityInstance(activityInstance: ActivityInstance)
}
