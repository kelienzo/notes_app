package com.kelly.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.kelly.notes.activity.NoteDetailsActivity
import com.kelly.notes.databinding.ActivityMainBinding
import com.kelly.notes.models.Note
import com.kelly.notes.models.NoteDataBase
import com.kelly.notes.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: NoteDataBase
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //instantiating database
        database = NoteDataBase.getInstance(applicationContext)


        //instantiating viewModel
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getNotes(database)

        //creating adapter
        viewModel.notesLiveData.observe(this, { notes ->
            noteAdapter = NoteAdapter(notes) {
                val intent = Intent(this, NoteDetailsActivity::class.java)
                intent.run {
                    putExtra("id", it.id)
                }
                startActivity(intent)
            }
            //refreshing recycler view
            binding.notesRecycler.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = noteAdapter
            }
        })

        binding.saveButton.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            saveNote(title, content)
        }
    }

    private fun saveNote(title: String, content: String) {
        val note = Note(id = 0, title, content)
        viewModel.addNote(database, note)
    }
}