package com.example.festifan.model

import com.google.gson.annotations.SerializedName

class WeatherResponse {

    data class Root(
        @SerializedName("liveweer") val results: List<Weather>
    )

    data class Weather(
        @SerializedName("plaats") val plaats: String,
        @SerializedName("temp") val tempratuur: String,
        @SerializedName("samenv") val samenvatting: String,
        @SerializedName("windr") val windrichting: String,
        @SerializedName("image") val image: String,
        @SerializedName("winds") val windkracht: String,
        @SerializedName("d0neerslag") val neerslagKans: String
    )
}
