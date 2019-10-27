package com.vitoksmile.reminder.domain.usecases

import androidx.lifecycle.LiveData
import com.vitoksmile.reminder.domain.models.Note

interface NotesUseCase {

    fun getNotes(): LiveData<List<Note>>

    suspend fun add(title: String, body: String)

    suspend fun update(note: Note, title: String, body: String)

    suspend fun delete(note: Note)
}