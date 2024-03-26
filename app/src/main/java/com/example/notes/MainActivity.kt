package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.room.Room
import com.example.notes.data.noteDatabase
import com.example.notes.presentation.NavGraphs
import com.example.notes.presentation.addNotesScreen
import com.example.notes.presentation.destinations.addNotesScreenDestination
import com.example.notes.presentation.destinations.notesScreenDestination
import com.example.notes.presentation.noteState
import com.example.notes.presentation.notesScreen
import com.example.notes.presentation.notesViewModel
import com.example.notes.ui.theme.NotesTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route
import com.ramcosta.composedestinations.utils.startDestination


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

                DestinationsNavHost(
                    navGraph = NavGraphs.root
                ){
                    composable(notesScreenDestination){
                        notesScreen(destinationsNavigator = destinationsNavigator, state = state, onEvent = viewModel::onEvent)
                    }
                    composable(addNotesScreenDestination){
                       addNotesScreen(state = state, onEvent = viewModel::onEvent , destinationsNavigator = destinationsNavigator)
                    }
                }
//                notesScreen(state = state, onEvent = viewModel::onEvent, destinationsNavigator = null)


            }
        }
    }
}

