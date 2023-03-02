package com.example.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotes()

    @Query("SELECT *FROM notes_table ORDER BY priority ASC")
    fun getAllNotes(): LiveData<MutableList<Note>>
}