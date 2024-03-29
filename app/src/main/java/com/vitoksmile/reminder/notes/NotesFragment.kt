package com.vitoksmile.reminder.notes

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vitoksmile.reminder.R
import com.vitoksmile.reminder.domain.models.Note
import com.vitoksmile.reminder.note.Action
import com.vitoksmile.reminder.notes.NotesFragmentDirections.Companion.actionAddNote
import kotlinx.android.synthetic.main.fragment_notes.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesFragment : Fragment(R.layout.fragment_notes) {

    private val viewModel: NotesViewModel by viewModel()

    private val adapter by lazy { NotesAdapter(::updateNote) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = adapter
        btnAdd.setOnClickListener { addNote() }

        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
        viewModel.notes.observe(viewLifecycleOwner, Observer {
            adapter.notes = it
            updateEmptyState()
        })
    }

    private fun updateEmptyState() {
        tvEmptyState.isVisible = adapter.itemCount == 0
    }

    private fun addNote() {
        findNavController().navigate(actionAddNote(Action.NewNote))
    }

    private fun updateNote(note: Note) {
        findNavController().navigate(actionAddNote(Action.UpdateNote(note.id)))
    }
}