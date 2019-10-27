package com.vitoksmile.reminder.data.repository

import com.vitoksmile.reminder.data.db.dao.NotesDao
import com.vitoksmile.reminder.data.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

class NotesRepositoryImpl(
    private val dao: NotesDao
) : NotesRepository {

    override fun getNotes(): Flow<List<NoteEntity>> {
        return dao.getNotes()
    }

    override suspend fun add(title: String, body: String, date: Date) {
        val entity = NoteEntity(title = title, body = body, date = date)
        dao.insertOrUpdate(entity)
    }

    override suspend fun update(note: NoteEntity, title: String, body: String, date: Date) {
        val entity = note.copy(title = title, body = body, date = date)
        dao.insertOrUpdate(entity)
    }

    override suspend fun delete(note: NoteEntity) {
        dao.delete(note)
    }
}