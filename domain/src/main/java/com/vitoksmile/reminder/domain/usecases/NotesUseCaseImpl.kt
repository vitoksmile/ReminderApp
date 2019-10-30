package com.vitoksmile.reminder.domain.usecases

import androidx.lifecycle.LiveData
import com.vitoksmile.reminder.data.repository.NotesRepository
import com.vitoksmile.reminder.domain.livedata.FlowLiveData
import com.vitoksmile.reminder.domain.livedata.asLiveData
import com.vitoksmile.reminder.domain.mappers.NoteMapper
import com.vitoksmile.reminder.domain.models.Note
import kotlinx.coroutines.flow.map
import java.util.*

class NotesUseCaseImpl(
    private val repository: NotesRepository
) : NotesUseCase {

    override fun getNotes(): LiveData<List<Note>> {
        return repository.getNotes().map {
            // Transform models from data to domain
            it.map(NoteMapper::toDomain)
        }.asLiveData()
    }

    override suspend fun add(title: String, body: String) {
        repository.add(title, body, Date())
    }

    override suspend fun update(note: Note, title: String, body: String) {
        repository.update(note.id, title, body, Date())
    }

    override suspend fun delete(note: Note) {
        repository.delete(note.id)
    }
}