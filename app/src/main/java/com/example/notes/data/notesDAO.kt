package com.example.notes.data

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

interface notesDAO {


    @Upsert
    suspend fun upsert(note: Notes)

    @Delete
    suspend fun delete(note: Notes)

    @Query("SELECT * FROM notes ORDER BY title ASC")
    fun sortByTitle(): Flow<List<Notes>>


    @Query("SELECT * FROM notes ORDER BY dateAdded")
    fun sortByDateAdded(): Flow<List<Notes>>

}