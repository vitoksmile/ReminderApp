package com.vitoksmile.reminder.domain.usecases

import androidx.lifecycle.LiveData
import com.vitoksmile.reminder.data.repository.NotesRepository
import com.vitoksmile.reminder.domain.coroutines.onBG
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

    override suspend fun getNote(id: Long) = onBG {
        NoteMapper.toDomain(repository.getNote(id))
    }

    override suspend fun add(title: String, body: String, date: Date?) = onBG {
        repository.add(title, body, date)
    }

    override suspend fun update(id: Long, title: String, body: String, date: Date?) = onBG {
        repository.update(id, title, body, date)
    }

    override suspend fun delete(id: Long) = onBG {
        repository.delete(id)
    }
}