package com.example.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    val title: String,
    val description: String,
    val priority: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}