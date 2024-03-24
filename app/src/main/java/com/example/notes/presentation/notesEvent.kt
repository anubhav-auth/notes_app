package com.example.notes.presentation

import android.icu.text.CaseMap.Title
import com.example.notes.data.Notes

sealed interface notesEvent {

    object sortNotes : notesEvent
    data class deleteNote(var note: Notes) : notesEvent
    data class saveNote(
        var title: String,
        var body: String,
    ) : notesEvent


}