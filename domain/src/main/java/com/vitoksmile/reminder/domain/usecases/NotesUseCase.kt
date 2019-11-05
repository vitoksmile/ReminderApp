package com.vitoksmile.reminder.domain.usecases

import androidx.lifecycle.LiveData
import com.vitoksmile.reminder.domain.models.Note
import java.util.*

interface NotesUseCase {

    fun getNotes(): LiveData<List<Note>>

    suspend fun getNote(id: Long): Note

    suspend fun add(title: String, body: String, date: Date?)

    suspend fun update(id: Long, title: String, body: String, date: Date?)

    suspend fun delete(id: Long)
}