package com.jayesh.notesapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.jayesh.notesapp.data.NotesDatabase

class NotesApplication : Application() {
    val database: NotesDatabase by lazy { NotesDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        applyTheme()
    }

    private fun applyTheme() {
        val sharedPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val isDarkMode = sharedPrefs.getBoolean(KEY_DARK_MODE, false)
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    companion object {
        private const val PREFS_NAME = "NotesAppPrefs"
        private const val KEY_DARK_MODE = "dark_mode"
    }
}