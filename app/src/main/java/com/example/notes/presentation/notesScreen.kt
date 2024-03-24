package com.example.notes.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.notes.ui.theme.Pink80

@Composable
fun notesScreen(
    state: noteState, onEvent: (notesEvent) -> Unit
) {
    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(Pink80)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Notes App",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            IconButton(onClick = { onEvent(notesEvent.sortNotes) }) {
                Icon(
                    imageVector = Icons.Rounded.Sort,
                    contentDescription = null,
                    modifier = Modifier.size(15.dp),
                    tint = Pink80
                )
            }
        }
    },

        floatingActionButton = {
            FloatingActionButton(containerColor = Pink80, onClick = {
                state.title.value = ""
                state.body.value = ""
            }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "add note")
            }
        }) {
        LazyColumn(
            contentPadding = it,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.notes.size){
                it ->
                NoteItem(
                    state = state,
                    index = it,
                    onEvent = onEvent
                )
            }
        }
    }

}

@Composable
fun NoteItem(state: noteState, index: Int, onEvent: (notesEvent) -> Unit) {

}
