package com.example.festifan.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Map(

    var name:String

    ) : Parcelable {companion object{
    val mapNames = arrayOf(
        "Tomorrowland",
        "Defqon",
        "Pinkpop"
        )
}}