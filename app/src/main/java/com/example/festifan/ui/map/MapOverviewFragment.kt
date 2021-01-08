package com.example.festifan.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.festifan.R
import com.example.festifan.model.Map
import kotlinx.android.synthetic.main.fragment_map_overview.*

const val BUNDLE_MAP_KEY = "bundle_map_key"
const val REQ_MAP_KEY = "req_map_key"

class MapOverviewFragment : Fragment() {

    private val maps = arrayListOf<Map>()
    private val mapAdapter = MapAdapter(maps, ::onMapClick)
    private var mapsAdded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Map overview"

        initViews()

    }

    private fun onMapClick(map : Map) {
        setFragmentResult(REQ_MAP_KEY, bundleOf(Pair(BUNDLE_MAP_KEY, map)))
        activity?.title = map.name + " map"
        findNavController().navigate(R.id.action_mapOverviewFragment_to_mapDisplayFragment)
    }

    private fun initViews() {

            rv_maps.adapter = mapAdapter
            rv_maps.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        if (!mapsAdded)
            for (i in Map.mapNames.indices) {
                maps.add(Map(Map.mapNames[i]))
            }
        mapsAdded = true


    }
}