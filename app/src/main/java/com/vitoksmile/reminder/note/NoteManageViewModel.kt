package com.vitoksmile.reminder.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitoksmile.reminder.domain.models.Note
import com.vitoksmile.reminder.domain.usecases.NotesUseCase
import kotlinx.coroutines.launch

class NoteManageViewModel(
    private val useCase: NotesUseCase
) : ViewModel() {

    private val _noteData = MutableLiveData<Note>()
    val noteData: LiveData<Note> = _noteData

    private val _noteAddedAction = MutableLiveData<Unit>()
    val noteAddedAction: LiveData<Unit> = _noteAddedAction

    private var isInEditMode = false
    private var noteId: Long = -1

    fun init(action: Action) {
        if (action is Action.UpdateNote) {
            if (_noteData.value != null) return

            isInEditMode = true
            noteId = action.noteId

            viewModelScope.launch {
                _noteData.value = useCase.getNote(noteId)
            }
        }
    }

    fun manage(title: String, body: String) {
        viewModelScope.launch {
            if (isInEditMode) {
                useCase.update(noteId, title, body)
            } else {
                useCase.add(title, body)
            }
            _noteAddedAction.value = Unit
        }
    }
}