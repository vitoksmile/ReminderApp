package com.vitoksmile.reminder.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitoksmile.reminder.domain.usecases.NotesUseCase
import kotlinx.coroutines.launch

class NoteManageViewModel(
    private val useCase: NotesUseCase
) : ViewModel() {

    private val _noteAddedAction = MutableLiveData<Unit>()
    val noteAddedAction: LiveData<Unit> = _noteAddedAction

    fun add(title: String, body: String) {
        viewModelScope.launch {
            useCase.add(title, body)
            _noteAddedAction.value = Unit
        }
    }
}