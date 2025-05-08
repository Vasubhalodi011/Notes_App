package com.jayesh.notesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.jayesh.notesapp.R
import com.jayesh.notesapp.data.Note
import com.jayesh.notesapp.data.Priority
import com.jayesh.notesapp.ui.NotesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddEditNoteFragment : Fragment() {
    private val viewModel: NotesViewModel by viewModels()
    private var noteId: Long = -1

    private lateinit var titleEditText: TextInputEditText
    private lateinit var descriptionEditText: TextInputEditText
    private lateinit var prioritySpinner: Spinner
    private lateinit var saveFab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBackPressHandler()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_edit_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleEditText = view.findViewById(R.id.titleEditText)
        descriptionEditText = view.findViewById(R.id.descriptionEditText)
        prioritySpinner = view.findViewById(R.id.prioritySpinner)
        saveFab = view.findViewById(R.id.saveFab)

        setupPrioritySpinner()
        setupSaveButton()

        arguments?.getLong(ARG_NOTE_ID)?.let { id ->
            noteId = id
            loadNote(id)
        }
    }

    private fun setupBackPressHandler() {
        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (hasUnsavedChanges()) {
                    showUnsavedChangesDialog()
                } else {
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }
        })
    }

    private fun hasUnsavedChanges(): Boolean {
        val title = titleEditText.text.toString()
        val description = descriptionEditText.text.toString()
        return title.isNotBlank() || description.isNotBlank()
    }

    private fun showUnsavedChangesDialog() {
        com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
            .setTitle("Unsaved Changes")
            .setMessage("Do you want to save your changes?")
            .setPositiveButton("Save") { _, _ ->
                saveNote()
            }
            .setNegativeButton("Discard") { _, _ ->
                requireActivity().onBackPressed()
            }
            .setNeutralButton("Cancel", null)
            .show()
    }

    private fun setupPrioritySpinner() {
        val priorities = Priority.values()
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            priorities
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        prioritySpinner.adapter = adapter
    }

    private fun setupSaveButton() {
        saveFab.setOnClickListener {
            saveNote()
        }
    }

    private fun loadNote(id: Long) {
        viewModel.allNotes.observe(viewLifecycleOwner) { notes ->
            notes.find { it.id == id }?.let { note ->
                titleEditText.setText(note.title)
                descriptionEditText.setText(note.description)
                prioritySpinner.setSelection(note.priority.ordinal)
            }
        }
    }

    private fun saveNote() {
        val title = titleEditText.text.toString()
        val description = descriptionEditText.text.toString()
        val priority = prioritySpinner.selectedItem as Priority

        if (title.isNotBlank()) {
            val note = Note(
                id = if (noteId == -1L) 0 else noteId,
                title = title,
                description = description,
                priority = priority
            )

            CoroutineScope(Dispatchers.IO).launch {
                if (noteId == -1L) {
                    viewModel.insert(note)
                } else {
                    viewModel.update(note)
                }
                withContext(Dispatchers.Main) {
                    view?.let {
                        Snackbar.make(it, "Note saved", Snackbar.LENGTH_SHORT).show()
                    }
                    // Clear the fields after saving
                    titleEditText.text?.clear()
                    descriptionEditText.text?.clear()
                    prioritySpinner.setSelection(0)
                    
                    // If we're editing an existing note, go back
                    if (noteId != -1L) {
                        requireActivity().onBackPressed()
                    }
                }
            }
        } else {
            view?.let {
                Snackbar.make(it, "Title cannot be empty", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val ARG_NOTE_ID = "note_id"

        fun newInstance(noteId: Long = -1L) = AddEditNoteFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG_NOTE_ID, noteId)
            }
        }
    }
} 