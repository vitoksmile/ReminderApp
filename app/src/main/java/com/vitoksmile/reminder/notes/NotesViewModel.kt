package com.vitoksmile.reminder.notes

import androidx.lifecycle.ViewModel
import com.vitoksmile.reminder.domain.usecases.NotesUseCase

class NotesViewModel(
    useCase: NotesUseCase
) : ViewModel() {

    val notes = useCase.getNotes()
}