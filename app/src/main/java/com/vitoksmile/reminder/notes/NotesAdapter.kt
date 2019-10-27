package com.vitoksmile.reminder.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitoksmile.reminder.R
import com.vitoksmile.reminder.domain.models.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    var notes: List<Note> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount() = notes.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvTitle = view.tvTitle
        private val tvBody = view.tvBody
        private val tvDate = view.tvDate

        fun bind(note: Note) {}
    }
}