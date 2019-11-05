package com.vitoksmile.reminder.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.vitoksmile.reminder.R
import com.vitoksmile.reminder.domain.models.Note
import com.vitoksmile.reminder.utils.formatNoteDate
import kotlinx.android.synthetic.main.item_note.view.*

class NotesAdapter(
    private val clickListener: (note: Note) -> Unit
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

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

        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position == RecyclerView.NO_POSITION) return@setOnClickListener
                clickListener(notes[position])
            }
        }

        fun bind(note: Note) = with(note) {
            tvTitle.isVisible = title.isNotEmpty()
            tvTitle.text = title
            tvBody.text = body
            tvDate.isVisible = date != null
            tvDate.text = date?.formatNoteDate()
        }
    }
}