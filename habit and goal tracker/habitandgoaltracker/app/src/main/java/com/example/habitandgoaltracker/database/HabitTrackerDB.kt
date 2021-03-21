package com.example.habitandgoaltracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.habitandgoaltracker.database.entities.*

@Database(
    entities = [Category::class, Activity::class, Property::class, ActivityInstance::class],
    version = 1,
    exportSchema = false
)
abstract class HabitTrackerDB : RoomDatabase() {
    abstract fun habitTrackerDAO(): HabitTrackerDAO

    companion object {
        @Volatile
        private var INSTANCE: HabitTrackerDB? = null
        val data: DBData = DBData()

        fun getInstance(context: Context): HabitTrackerDB {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = createInstance(context)
                }
                return INSTANCE!!
            }
        }

        private fun createInstance(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                HabitTrackerDB::class.java,
                "DataBase.db"
            )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Thread(Runnable { prepopulateDb(context, getInstance(context)) }).start()
                    }
                })
                .allowMainThreadQueries()
                .build()

        private fun prepopulateDb(context: Context, instance: HabitTrackerDB) {
            instance.habitTrackerDAO().insertCategories(data.categories)
        }
    }
}
