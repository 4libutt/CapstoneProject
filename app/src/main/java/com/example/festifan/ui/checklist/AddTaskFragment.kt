package com.example.festifan.ui.checklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.festifan.R
import com.example.festifan.model.Task
import com.example.festifan.viewmodel.TaskViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_checklist.*

class AddTaskFragment : Fragment() {

    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_checklist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Add task"

        fab_add_checklistItem.setOnClickListener {
            addGame()
            findNavController().popBackStack()
        }
    }

    private fun addGame() {

        val name = et_taskName.text.toString()
        val checked = checkbox_important.isChecked

        if (name.isEmpty()) {
            Snackbar.make(requireView(), "Enter a title", Snackbar.LENGTH_SHORT).show()
            return
        }
        taskViewModel.addTask(Task(name = name, important = checked))
    }
}