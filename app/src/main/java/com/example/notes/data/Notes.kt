package com.example.notes.data

import android.icu.text.CaseMap.Title
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notes(

    @PrimaryKey(autoGenerate = true)
    val key : Int = 0,

    val title: String,
    val body: String,
    val dateAdded :Long
)
