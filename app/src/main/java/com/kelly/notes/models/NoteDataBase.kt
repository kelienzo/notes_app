package com.kelly.notes.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDAO

    companion object {
        private var dataBase: NoteDataBase? = null
        fun getInstance(context: Context): NoteDataBase {
            return if (dataBase != null) {
                return dataBase!!
            } else {
                dataBase = Room.databaseBuilder(context, NoteDataBase::class.java, "notes_database")
                    .build()
                dataBase!!
            }
        }
    }
}