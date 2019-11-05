package com.vitoksmile.reminder.domain.mappers

import com.vitoksmile.reminder.data.db.entity.NoteEntity
import com.vitoksmile.reminder.domain.models.Note

object NoteMapper {

    fun toDomain(entity: NoteEntity) = with(entity) {
        Note(id, title, body, date, updatedAt)
    }
}