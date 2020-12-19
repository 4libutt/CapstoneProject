package com.example.festifan.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "checklist")
@Parcelize
data class Task(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Long? = null,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "created")
        var created: Long = System.currentTimeMillis(),

        @ColumnInfo(name = "important")
        var important: Boolean = false,

        @ColumnInfo(name = "completed")
        var completed: Boolean = false,

) : Parcelable {
        val createdDateFormatted: String
        get() = DateFormat.getDateInstance().format(created)
}
