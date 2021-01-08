package com.example.festifan.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.festifan.R
import com.example.festifan.model.Map
import kotlinx.android.synthetic.main.fragment_map_display.*

class MapDisplayFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map_display, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeFragmentResult()
    }

    private fun observeFragmentResult() {
        setFragmentResultListener(REQ_MAP_KEY) { _, bundle ->
            bundle.getParcelable<Map>(BUNDLE_MAP_KEY)?.let { setElements(it) }
        }
    }

    private fun setElements(map: Map) {
        when (map.name) {
            "Tomorrowland" -> iv_map.setImageResource(R.drawable.tomorrrowland_map)
            "Defqon" -> iv_map.setImageResource(R.drawable.defqon_map)
            "Pinkpop" -> iv_map.setImageResource(R.drawable.pinkpop_map)
        }

    }

}