package com.vitoksmile.reminder.domain

import com.vitoksmile.reminder.data.repository.NotesRepository
import com.vitoksmile.reminder.domain.mappers.NoteMapper
import com.vitoksmile.reminder.domain.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesUseCase(
    private val repository: NotesRepository
) {

    fun getNotes(): Flow<List<Note>> {
        return repository.getNotes().map {
            it.map(NoteMapper::toDomain)
        }
    }

    suspend fun add(title: String, body: String) {}

    suspend fun update(note: Note, title: String, body: String) {}

    suspend fun delete(note: Note) {}
}