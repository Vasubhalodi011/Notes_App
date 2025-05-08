package com.jayesh.notesapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.jayesh.notesapp.data.Note
import com.jayesh.notesapp.data.NotesRepository
import com.jayesh.notesapp.data.Priority
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NotesRepository
    val allNotes: LiveData<List<Note>>

    init {
        val notesDao = (application as com.jayesh.notesapp.NotesApplication).database.noteDao()
        repository = NotesRepository(notesDao)
        allNotes = repository.allNotes
    }

    fun getNotesByPriority(priority: Priority): LiveData<List<Note>> {
        return repository.getNotesByPriority(priority)
    }

    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    fun update(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }
} 