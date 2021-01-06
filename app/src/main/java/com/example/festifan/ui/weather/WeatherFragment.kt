package com.example.festifan.ui.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.festifan.MainActivity
import com.example.festifan.R
import com.example.festifan.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_weather.*
import java.util.Date


@Suppress("DEPRECATION")
class WeatherFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var location: String
    private val weatherPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity: MainActivity? = activity as MainActivity?
        location = activity?.getWeatherLocation()!!
        weatherViewModel.getWeather(location)
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {

                val newLocation = activity.getWeatherLocation()

                if (location != newLocation) {
                    location = newLocation
                    weatherViewModel.getWeather(location)
                }
                handler.postDelayed(this, 3000)
            }
        }, 0)
        observeWeather()
    }

    @SuppressLint("SetTextI18n")
    private fun observeWeather() {
        val day = DateFormat.format("dd", Date()) as String
        val month = DateFormat.format("MMM", Date()) as String
        val currentDate = "$day $month"

        weatherViewModel.weather.observe(viewLifecycleOwner, {
            tv_plaats.text = weatherViewModel.weather.value?.get(weatherPosition)?.plaats
            tv_tempratuur.text = weatherViewModel.weather.value?.get(weatherPosition)?.tempratuur + " °C"
            tv_kans_neerslag.text = weatherViewModel.weather.value?.get(weatherPosition)?.neerslagKans + "% regen"
            tv_windr.text = weatherViewModel.weather.value?.get(weatherPosition)?.windrichting +
                    ", windkracht " + weatherViewModel.weather.value?.get(weatherPosition)?.windkracht
            tv_dag.text = currentDate
            tv_samenvatting.text = weatherViewModel.weather.value?.get(weatherPosition)?.samenvatting
            chooseImage(weatherViewModel.weather.value?.get(weatherPosition)?.image)
        })

        // Observe the error message.
        weatherViewModel.errorText.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun chooseImage(image: String?) {
        when (image) {
            "zonnig" -> iv_image.setImageResource(R.drawable.zonnig)
            "bliksem" -> iv_image.setImageResource(R.drawable.bliksem)
            "regen" -> iv_image.setImageResource(R.drawable.regen)
            "buien" -> iv_image.setImageResource(R.drawable.buien)
            "hagel" -> iv_image.setImageResource(R.drawable.hagel)
            "mist" -> iv_image.setImageResource(R.drawable.mist)
            "sneeuw" -> iv_image.setImageResource(R.drawable.sneeuw)
            "bewolkt" -> iv_image.setImageResource(R.drawable.bewolkt)
            "halfbewolkt" -> iv_image.setImageResource(R.drawable.halfbewolkt)
            "zwaarbewolkt" -> iv_image.setImageResource(R.drawable.zwaarbewolkt)
            "nachtmist" -> iv_image.setImageResource(R.drawable.nachtmist)
            "helderenacht" -> iv_image.setImageResource(R.drawable.helderenacht)
            "wolkennacht" -> iv_image.setImageResource(R.drawable.wolkennacht)
        }
    }


}