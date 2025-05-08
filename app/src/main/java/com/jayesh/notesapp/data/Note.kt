package com.jayesh.notesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val priority: Priority
)

enum class Priority(val color: Int) {
    HIGH(android.graphics.Color.RED),
    URGENT(android.graphics.Color.parseColor("#FF5722")), // Deep Orange
    MEDIUM(android.graphics.Color.parseColor("#FFC107")), // Amber
    NORMAL(android.graphics.Color.parseColor("#4CAF50"))  // Green
} 