package com.kelly.notes.viewmodels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.kelly.notes.models.Note
import com.kelly.notes.models.NoteDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {


    val notesLiveData = MutableLiveData<List<Note>>()

    fun getNotes(database: NoteDataBase) {
        viewModelScope.launch {
            notesLiveData.postValue(database.noteDao().getAllNotes())
        }
    }

    fun addNote(database: NoteDataBase, note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            database.noteDao().addNote(note)
            getNotes(database)
        }
    }
}