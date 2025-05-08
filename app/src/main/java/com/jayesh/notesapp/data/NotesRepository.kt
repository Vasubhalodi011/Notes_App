package com.jayesh.notesapp.data

import androidx.lifecycle.LiveData

class NotesRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    fun getNotesByPriority(priority: Priority): LiveData<List<Note>> {
        return noteDao.getNotesByPriority(priority)
    }

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
} 