package com.jayesh.notesapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jayesh.notesapp.ui.fragments.HomeFragment
import com.jayesh.notesapp.ui.fragments.AddEditNoteFragment
import com.jayesh.notesapp.ui.fragments.SettingsFragment

class NotesPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> AddEditNoteFragment()
            2 -> SettingsFragment()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
} 