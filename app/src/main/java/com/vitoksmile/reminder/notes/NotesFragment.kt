package com.vitoksmile.reminder.notes

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vitoksmile.reminder.R
import com.vitoksmile.reminder.data.db.AppDatabase
import com.vitoksmile.reminder.data.db.AppDatabase_Impl
import com.vitoksmile.reminder.data.repository.NotesRepositoryImpl
import com.vitoksmile.reminder.domain.usecases.NotesUseCaseImpl
import kotlinx.android.synthetic.main.fragment_notes.*

class NotesFragment : Fragment(R.layout.fragment_notes) {

    private val viewModel by lazy {
        // TODO: use DI
        val db = AppDatabase.getInstance(requireContext().applicationContext)
        val dao = db.getNotesDao()
        val repo = NotesRepositoryImpl(dao)
        val useCase = NotesUseCaseImpl(repo)
        NotesViewModel(useCase)
    }

    private val adapter by lazy { NotesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = adapter
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
}