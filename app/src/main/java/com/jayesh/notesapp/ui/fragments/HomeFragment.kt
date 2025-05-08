package com.jayesh.notesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.jayesh.notesapp.MainActivity
import com.jayesh.notesapp.R
import com.jayesh.notesapp.data.Note
import com.jayesh.notesapp.data.NotesDatabase
import com.jayesh.notesapp.ui.NotesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotesAdapter
    private lateinit var database: NotesDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = NotesDatabase.getDatabase(requireContext())
        setupRecyclerView()
        observeNotes()
    }

    private fun setupRecyclerView() {
        recyclerView = requireView().findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        
        adapter = NotesAdapter(
            onNoteClick = { note ->
                (activity as? MainActivity)?.navigateToEditNote(note.id)
            },
            onDeleteClick = { note ->
                showDeleteConfirmationDialog(note)
            }
        )
        recyclerView.adapter = adapter
    }

    private fun observeNotes() {
        database.noteDao().getAllNotes().observe(viewLifecycleOwner) { notes ->
            adapter.submitList(notes)
        }
    }

    private fun showDeleteConfirmationDialog(note: Note) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete Note")
            .setMessage("Are you sure you want to delete this note?")
            .setPositiveButton("Delete") { _, _ ->
                deleteNote(note)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            database.noteDao().delete(note)
            withContext(Dispatchers.Main) {
                view?.let {
                    Snackbar.make(it, "Note deleted", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh notes when returning to this fragment
        observeNotes()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            // Refresh notes when fragment becomes visible
            observeNotes()
        }
    }
} 