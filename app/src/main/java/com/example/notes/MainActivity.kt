package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.notes.data.noteDatabase
import com.example.notes.presentation.notesScreen
import com.example.notes.presentation.notesViewModel
import com.example.notes.ui.theme.NotesTheme


class MainActivity : ComponentActivity() {

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            noteDatabase::class.java,
            "notes.db"
        ).build()
    }

    private val viewModel by viewModels<notesViewModel> (
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return notesViewModel(database.dao) as T
                }
            }
        }
    )

//    private val viewModel by lazy {
//        notesViewModel(database.dao)
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesTheme {
                val state by viewModel.state.collectAsState()

                notesScreen(state = state, onEvent = viewModel::onEvent)

            }
        }
    }
}