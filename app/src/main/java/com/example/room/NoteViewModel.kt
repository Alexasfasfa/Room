package com.example.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<MutableList<Note>>
    val repository: NoteRepository

    init {
        val dao = NoteDatabase.getInstance(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun addNote(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }

    fun deleteAllNote() = viewModelScope.launch {
        repository.deleteAllNotes()
    }
}