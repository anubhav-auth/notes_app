package com.example.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Notes::class],
    version = 1
)
abstract class noteDataabase : RoomDatabase(){
    abstract val dao: notesDAO
}