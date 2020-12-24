package com.example.festifan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.festifan.model.Task
import com.example.festifan.repository.TaskRepository
import kotlinx.coroutines.*

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val taskRepository = TaskRepository(application.applicationContext)

    val taskLiveData: LiveData<List<Task>> get() = taskRepository.getAllTasks()

    fun addTask(task: Task) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                taskRepository.addTask(task)
            }
        }
    }

    fun deleteTask(task: Task) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                taskRepository.deleteTask(task)
            }
        }
    }

    fun deleteAllTasks() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                taskRepository.deleteAllTasks()
            }
        }
    }

    fun onTaskCheckedChanged(task: Task, isChecked: Boolean) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                taskRepository.updateTask(task.copy(completed = isChecked))
            }
        }
    }

    fun updateTask(task: Task, isChecked: Boolean) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                taskRepository.updateTask(task.copy(name = task.name, important = isChecked))
            }
        }
    }
}