package com.example.festifan.ui.map

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.festifan.R
import com.example.festifan.model.Map
import kotlinx.android.synthetic.main.item_map.view.*

class MapAdapter (private val maps: List<Map>, private val onClick: (Map) -> Unit) : RecyclerView.Adapter<MapAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        init {
            itemView.setOnClickListener {
                onClick(maps[adapterPosition])
            }
        }

        fun databind(map: Map) {
            itemView.tv_mapName.text = map.name

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_map, parent, false)
        )
    }


    override fun getItemCount(): Int {
        return maps.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(maps[position])
    }


}