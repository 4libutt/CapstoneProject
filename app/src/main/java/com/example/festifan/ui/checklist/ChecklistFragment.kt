package com.example.festifan.ui.checklist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.festifan.R
import com.example.festifan.model.Task
import com.example.festifan.viewmodel.TaskViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_checklist_overview.*

const val BUNDLE_TASK_KEY = "bundle_task_key"
const val REQ_TASK_KEY = "req_task_key"

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ChecklistFragment : Fragment(), TaskAdapter.OnItemClickListener  {

    private val taskViewModel: TaskViewModel by viewModels()
    private val tasks = arrayListOf<Task>()
    private val taskAdapter = TaskAdapter(tasks, this)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checklist_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Checklist"
        setHasOptionsMenu(true)
        initRv()
        observeLiveData()

        addfab.setOnClickListener {
            findNavController().navigate(R.id.action_ChecklistOverviewFragment_to_addTaskFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_tasks, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         return when(item.itemId) {
            R.id.action_delete -> {
                showDeletionDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("InflateParams")
    private fun showDeletionDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.delete_all_tasks))
            .setMessage(resources.getString(R.string.deletion_text))
            .setNegativeButton(resources.getString(R.string.cancel_button)) { _, _ ->
                return@setNegativeButton
            }
            .setPositiveButton(resources.getString(R.string.delete_button)) { _, _ ->
                taskViewModel.deleteAllTasks()
            }
            .show()
    }

    private fun observeLiveData() {
        taskViewModel.taskLiveData.observe(
            viewLifecycleOwner
        ) { liveTasks: List<Task> ->
            tasks.clear()
            tasks.addAll(liveTasks)
            taskAdapter.notifyDataSetChanged()
        }
    }

    private fun initRv() {
        rvTasks.apply {
            adapter = taskAdapter
            layoutManager =  LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            rvTasks.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        createItemTouchHelper().attachToRecyclerView(rvTasks)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val backlogTask = tasks[position]
                val undoSnackBar = Snackbar.make(
                    requireView(),
                    getString(R.string.removed_task),
                    Snackbar.LENGTH_SHORT
                ).setAction(getString(R.string.undo), fun(_: View) {
                    taskViewModel.addTask(backlogTask)
                })
                taskViewModel.deleteTask(backlogTask)
                undoSnackBar.show()
            }
        }
        return ItemTouchHelper(callback)
    }

    override fun onItemClick(task: Task) {
        setFragmentResult(REQ_TASK_KEY, bundleOf(Pair(BUNDLE_TASK_KEY, task)))
        findNavController().navigate(R.id.action_ChecklistOverviewFragment_to_editTaskFragment)
    }

    override fun onCheckBoxClick(task: Task, isChecked: Boolean) {
        taskViewModel.onTaskCheckedChanged(task, isChecked)
    }
}