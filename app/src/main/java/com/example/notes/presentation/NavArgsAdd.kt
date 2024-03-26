package com.example.notes.presentation

data class navArgs(
    val state: noteState,
    val onEvent: (notesEvent) -> Unit,
)
