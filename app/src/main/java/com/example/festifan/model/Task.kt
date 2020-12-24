package com.example.festifan.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "task")
@Parcelize
data class Task(

        var name: String,
        var important: Boolean = false,
        var completed: Boolean = false,
        var created: Long = System.currentTimeMillis(),
        @PrimaryKey(autoGenerate = true)
        var id: Long? = null

) : Parcelable {
        val createdDateFormatted: String
        get() = DateFormat.getDateInstance().format(created)
}
