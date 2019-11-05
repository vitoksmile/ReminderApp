package com.vitoksmile.reminder.data.repository

import com.vitoksmile.reminder.data.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

interface NotesRepository {

    fun getNotes(): Flow<List<NoteEntity>>

    suspend fun getNote(id: Long): NoteEntity

    suspend fun add(title: String, body: String, date: Date?)

    suspend fun update(id: Long, title: String, body: String, date: Date?)

    suspend fun delete(id: Long)
}