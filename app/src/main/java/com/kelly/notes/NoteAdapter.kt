package com.kelly.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kelly.notes.databinding.NoteListBinding
import com.kelly.notes.models.Note

class NoteAdapter(private val notes: List<Note>, val clicker: (Note) -> Unit) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(private val binding: NoteListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setNote(note: Note) {
            binding.apply {
                idDisplay.text = note.id.toString()
                titleDisplay.text = note.title
                root.setOnClickListener { clicker(note) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding: NoteListBinding = NoteListBinding.inflate(LayoutInflater.from(parent.context))
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        //val note = notes[position]
        holder.setNote(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}