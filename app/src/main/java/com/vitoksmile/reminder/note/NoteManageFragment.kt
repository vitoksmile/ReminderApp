package com.vitoksmile.reminder.note

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vitoksmile.reminder.R
import com.vitoksmile.reminder.data.db.AppDatabase
import com.vitoksmile.reminder.data.repository.NotesRepositoryImpl
import com.vitoksmile.reminder.domain.usecases.NotesUseCaseImpl
import com.vitoksmile.reminder.extensions.inputtedText
import com.vitoksmile.reminder.extensions.observe
import kotlinx.android.synthetic.main.fragment_note_manage.*

class NoteManageFragment : Fragment(R.layout.fragment_note_manage) {

    private val viewModel by lazy {
        // TODO: use DI
        val db = AppDatabase.getInstance(requireContext().applicationContext)
        val dao = db.getNotesDao()
        val repo = NotesRepositoryImpl(dao)
        val useCase = NotesUseCaseImpl(repo)
        NoteManageViewModel(useCase)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener { back() }
        btnAdd.setOnClickListener { add() }

        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
        observe(viewModel.noteAddedAction) { back() }
    }

    private fun back() {
        findNavController().popBackStack()
    }

    private fun add() {
        viewModel.add(
            title = editTitle.inputtedText,
            body = editBody.inputtedText
        )
    }
}