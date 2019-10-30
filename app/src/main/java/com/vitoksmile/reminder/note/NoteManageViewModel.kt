package com.vitoksmile.reminder.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitoksmile.reminder.livedata.ActionLiveData
import com.vitoksmile.reminder.domain.models.Note
import com.vitoksmile.reminder.domain.usecases.NotesUseCase
import kotlinx.coroutines.launch

class NoteManageViewModel(
    private val useCase: NotesUseCase
) : ViewModel() {

    private val _noteData = MutableLiveData<Note>()
    val noteData: LiveData<Note> = _noteData

    val backAction = ActionLiveData()

    private val _validationData = MutableLiveData<NoteValidator.Status>()
    private val validator = NoteValidator(_validationData)
    val validationData: LiveData<NoteValidator.Status> = _validationData

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
        if (!validator.isValid(title, body)) return

        viewModelScope.launch {
            if (isInEditMode) {
                useCase.update(noteId, title, body)
            } else {
                useCase.add(title, body)
            }
            backAction.sendAction()
        }
    }

    fun delete() {
        if (!isInEditMode) return

        viewModelScope.launch {
            useCase.delete(noteId)
            backAction.sendAction()
        }
    }
}