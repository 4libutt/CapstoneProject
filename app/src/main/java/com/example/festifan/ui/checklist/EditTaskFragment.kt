package com.example.festifan.ui.checklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.festifan.R
import com.example.festifan.model.Task
import com.example.festifan.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_edit_task.*

class EditTaskFragment : Fragment() {

    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var currentTask: Task

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFragmentResult()
        activity?.title = "Edit task"

        fab_save_checklistItem.setOnClickListener {
            checkTask()
            taskViewModel.updateTask(currentTask, currentTask.important)
            findNavController().popBackStack()
        }
    }

    private fun observeFragmentResult() {
        setFragmentResultListener(REQ_TASK_KEY) { _, bundle ->
            bundle.getParcelable<Task>(BUNDLE_TASK_KEY)?.let { setElements(it) }
        }
    }

    private fun setElements(task: Task) {
        currentTask = task
        et_taskName.setText(task.name)
        checkbox_important.isChecked = task.important
        checkbox_important.jumpDrawablesToCurrentState()
        tv_date.text = "Created: ${task.createdDateFormatted}"

    }

    private fun checkTask() {
        currentTask.name = et_taskName.text.toString()
        currentTask.important = checkbox_important.isChecked
    }
}