package com.example.notes.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.notes.data.Notes

data class noteState (

    val notes: List<Notes> = emptyList(),
    val title: MutableState<String> = mutableStateOf(""),
    val body: MutableState<String> = mutableStateOf("")

    )