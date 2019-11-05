package com.vitoksmile.reminder.note

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vitoksmile.reminder.R
import com.vitoksmile.reminder.dialogs.ConfirmDialog
import com.vitoksmile.reminder.dialogs.pickDateTime
import com.vitoksmile.reminder.extensions.*
import com.vitoksmile.reminder.utils.formatNoteDate
import kotlinx.android.synthetic.main.fragment_note_manage.*
import kotlinx.android.synthetic.main.fragment_note_manage.toolbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class NoteManageFragment : Fragment(R.layout.fragment_note_manage),
    ConfirmDialog.OnConfirmListener {

    private companion object {
        const val ACTION_DELETE = "ACTION_DELETE"
    }

    private val viewModel: NoteManageViewModel by viewModel()

    private val args: NoteManageFragmentArgs by navArgs()
    private inline val isInEditMode get() = args.action is Action.UpdateNote

    private var datePicker: DialogFragment? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateState()
        toolbar.setNavigationOnClickListener { back() }
        tvDate.setOnClickListener { pickDate() }
        tvDate.onDrawableEndClicked { viewModel.clearDate() }
        btnManage.setOnClickListener { manage() }

        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
        viewModel.init(args.action)

        observe(viewModel.noteData) {
            editTitle.inputtedText = title
            editBody.inputtedText = body
        }
        observe(viewModel.dateData) {
            updateDate(this)
        }

        observe(viewModel.validationData) {
            when (this) {
                is NoteValidator.Status.Title -> inputTitle.bindError(errorResId)
                is NoteValidator.Status.Body -> inputBody.bindError(errorResId)
            }
        }
        observe(viewModel.backAction) { back() }
    }

    private fun updateState() {
        val title = if (isInEditMode) R.string.update_note_title else R.string.add_note_title
        toolbar.setTitle(title)

        val button = if (isInEditMode) R.string.update_note else R.string.add_note
        btnManage.setText(button)

        if (isInEditMode) {
            toolbar.inflateMenu(R.menu.note_manage)
            toolbar.setOnMenuItemClickListener {
                if (it.itemId == R.id.action_delete) deleteConfirm()
                true
            }
        }
    }

    private fun updateDate(date: Date?) {
        if (date != null) {
            tvDate.text = getString(R.string.note_remind_date_regexp, date.formatNoteDate())
            tvDate.setDrawableEnd(R.drawable.ic_date_clear)
        } else {
            tvDate.setText(R.string.note_remind_disabled)
            tvDate.clearDrawableEnd()
        }
    }

    private fun back() {
        findNavController().popBackStack()
    }

    private fun manage() {
        viewModel.manage(
            title = editTitle.inputtedText,
            body = editBody.inputtedText
        )
    }

    private fun pickDate() {
        pickDateTime(selectedDate = viewModel.currentDate) {
            viewModel.onDatePicked(it)
        }
    }

    private fun deleteConfirm() {
        ConfirmDialog.show(
            this,
            title = R.string.delete_note_confirm_title,
            message = R.string.delete_note_confirm_message,
            button = R.string.delete,
            action = ACTION_DELETE
        )
    }

    override fun onConfirmed(action: String) {
        if (action == ACTION_DELETE) viewModel.delete()
    }

    override fun onPause() {
        super.onPause()
        datePicker?.dismissAllowingStateLoss()
    }
}