package com.example.festifan.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.festifan.dao.TaskDao
import com.example.festifan.database.FestifanDatabase
import com.example.festifan.model.Task

class TaskRepository(context: Context) {

    private val taskDao: TaskDao

    init {
        val database = FestifanDatabase.getDatabase(context)
        taskDao = database!!.taskDao()
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getAllTasks()
    }

    fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.update(task)
    }

    suspend fun deleteAllTasks() {
        taskDao.deleteAllTasks()
    }

    fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
}