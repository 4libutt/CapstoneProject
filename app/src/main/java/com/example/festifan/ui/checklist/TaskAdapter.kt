package com.example.festifan.ui.checklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.festifan.R
import com.example.festifan.model.Task
import kotlinx.android.synthetic.main.fragment_checklist_item.view.*

class TaskAdapter(private val tasks: List<Task>, private val listener: OnItemClickListener) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener.onItemClick(tasks[adapterPosition])
                }
            }
            itemView.checkboxItem.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener.onCheckBoxClick(tasks[adapterPosition], itemView.checkboxItem.isChecked)
                }
            }
        }

        fun dataBind(task: Task) {
            itemView.checkboxItem.isChecked = task.completed
            itemView.tvName.text = task.name
            itemView.tvName.paint.isStrikeThruText = task.completed
            itemView.label_priority.isVisible = task.important
        }
    }

    interface OnItemClickListener {
        fun onItemClick(task: Task)
        fun onCheckBoxClick(task: Task, isChecked: Boolean)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_checklist_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}