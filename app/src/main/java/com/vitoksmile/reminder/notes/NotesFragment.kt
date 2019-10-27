package com.vitoksmile.reminder.notes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.vitoksmile.reminder.R
import kotlinx.android.synthetic.main.fragment_notes.*

class NotesFragment : Fragment(R.layout.fragment_notes) {

    private val adapter by lazy { NotesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = adapter
    }
}