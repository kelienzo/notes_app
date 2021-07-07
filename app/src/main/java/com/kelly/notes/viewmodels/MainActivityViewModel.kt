package com.kelly.notes.viewmodels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.kelly.notes.models.Note
import com.kelly.notes.models.NoteDataBase

class MainActivityViewModel : ViewModel() {


    val notesLiveData = MutableLiveData<List<Note>>()

    fun getNotes(database: NoteDataBase) {
        notesLiveData.postValue(database.noteDao().getAllNotes())
    }

    fun addNote(database: NoteDataBase, note: Note) {
        database.noteDao().addNote(note)
        getNotes(database)
    }
}