package com.example.festifan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.festifan.dao.TaskDao
import com.example.festifan.model.Task

@Database(entities = arrayOf(Task::class), version = 1, exportSchema = false)
abstract class FestifanDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        private const val DATABASE_NAME = "FESTIFAN_DATABASE"


        @Volatile
        private var festifanDatabase: FestifanDatabase? = null

        fun getDatabase(context: Context): FestifanDatabase? {
            if(festifanDatabase == null) {
                synchronized(FestifanDatabase::class.java) {
                    if(festifanDatabase == null) {
                        festifanDatabase = Room.databaseBuilder(
                            context.applicationContext,
                            FestifanDatabase::class.java,
                            DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return festifanDatabase
        }
    }
}