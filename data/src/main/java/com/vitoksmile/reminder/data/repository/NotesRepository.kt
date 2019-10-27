package com.vitoksmile.reminder.data.repository

import com.vitoksmile.reminder.data.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

interface NotesRepository {

    fun getNotes(): Flow<List<NoteEntity>>

    suspend fun add(title: String, body: String, date: Date)

    suspend fun update(note: NoteEntity, title: String, body: String, date: Date)

    suspend fun delete(note: NoteEntity)
}