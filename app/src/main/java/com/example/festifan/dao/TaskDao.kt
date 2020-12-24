package com.example.festifan.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.festifan.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task ORDER BY important DESC, name")
    fun getAllTasks(): LiveData<List<Task>>

    @Update
    suspend fun update(task: Task)

    @Insert
    fun addTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("DELETE FROM task")
    suspend fun deleteAllTasks()
}