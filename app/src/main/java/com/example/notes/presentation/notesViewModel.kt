package com.example.notes.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.Notes
import com.example.notes.data.notesDAO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class notesViewModel(
    private var dao: notesDAO
): ViewModel() {

    private var isSortedByDate = MutableStateFlow(true)

    @OptIn(ExperimentalCoroutinesApi::class)
    private var notes = isSortedByDate.flatMapLatest {
        if (it){
            dao.sortByDateAdded()
        }
        else {
            dao.sortByTitle()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    var _state = MutableStateFlow(noteState())

    var state = combine(_state, isSortedByDate, notes){
        state, isSortedByDate, notes ->
        state.copy(
            notes = notes
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), noteState())

    fun onEvent(event: notesEvent){
        when(event){
            is notesEvent.deleteNote -> {
                viewModelScope.launch {
                    dao.delete(event.note)
                }
            }
            is notesEvent.saveNote -> {
                viewModelScope.launch {
                    val note = Notes(
                       title = state.value.title.value,
                        body = state.value.body.value,
                        dateAdded = System.currentTimeMillis()
                    )
                    dao.upsert(note)
                }

                _state.update {
                    it.copy(
                        title = mutableStateOf(""),
                        body = mutableStateOf(""),
                    )
                }
            }
            notesEvent.sortNotes -> {
                isSortedByDate.value = !isSortedByDate.value
            }
        }
    }
}