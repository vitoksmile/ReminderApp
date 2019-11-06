package com.vitoksmile.reminder.domain.usecases

import androidx.lifecycle.LiveData
import com.vitoksmile.reminder.data.repository.NotesRepository
import com.vitoksmile.reminder.domain.coroutines.onBG
import com.vitoksmile.reminder.domain.livedata.asLiveData
import com.vitoksmile.reminder.domain.mappers.NoteMapper
import com.vitoksmile.reminder.domain.models.Note
import com.vitoksmile.reminder.scheduler.Scheduler
import com.vitoksmile.reminder.scheduler.Task
import kotlinx.coroutines.flow.map
import java.util.*

class NotesUseCaseImpl(
    private val repository: NotesRepository
) : NotesUseCase {

    private companion object {

        const val SERVICE_CLASS_NAME = "com.vitoksmile.reminder.notifications.NotificationsService"
    }

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
        val noteId = repository.add(title, body, date)
        date?.let { schedule(noteId, it) }
        Unit
    }

    override suspend fun update(id: Long, title: String, body: String, date: Date?) = onBG {
        repository.update(id, title, body, date)
        date?.let { schedule(id, it) }
        Unit
    }

    override suspend fun delete(id: Long) = onBG {
        repository.delete(id)
        unSchedule(id)
    }

    private fun schedule(noteId: Long, date: Date) {
        Scheduler.schedule(
            Task(
                id = noteId,
                time = date,
                serviceClassName = SERVICE_CLASS_NAME
            )
        )
    }

    private fun unSchedule(noteId: Long) {
        Scheduler.unSchedule(noteId)
    }
}