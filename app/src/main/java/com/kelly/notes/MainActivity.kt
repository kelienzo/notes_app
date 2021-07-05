package com.kelly.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.kelly.notes.databinding.ActivityMainBinding
import com.kelly.notes.models.Note
import com.kelly.notes.models.NoteDataBase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: NoteDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(this, NoteDataBase::class.java, "notes_database").build()
    }

    private fun saveNote(title: String, content: String){
        val note = Note(id = 0, title, content)
    }
}