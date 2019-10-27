package com.vitoksmile.reminder.data.db.dao

import androidx.room.*
import com.vitoksmile.reminder.data.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Suppress("FunctionName")
@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<NoteEntity>>

    @Transaction
    suspend fun insertOrUpdate(note: NoteEntity) {
        if (_insert(note) == -1L) _update(note)
    }

    @Delete
    suspend fun delete(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun _insert(note: NoteEntity): Long

    @Update
    suspend fun _update(note: NoteEntity)
}