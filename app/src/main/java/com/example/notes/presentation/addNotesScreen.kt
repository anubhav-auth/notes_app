package com.example.notes.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo


@Composable
fun addNotesScreen(
    state: noteState,
    onEvent: (notesEvent) -> Unit
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(
                        notesEvent.saveNote(
                            title = state.title.value,
                            body = state.body.value
                        )
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "save new note"
                )
            }
        }
    ) {
        Column (
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ){
            OutlinedTextField(value = state.title.value,
                onValueChange ={
                    state.title.value = it
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = {
                    Text(text = "title")
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            )
            OutlinedTextField(value = state.body.value,
                onValueChange ={
                    state.body.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = {
                    Text(text = "body")
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            )
        }
    }
}