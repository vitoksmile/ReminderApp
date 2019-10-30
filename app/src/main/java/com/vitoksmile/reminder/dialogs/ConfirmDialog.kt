package com.vitoksmile.reminder.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.vitoksmile.reminder.R

class ConfirmDialog : DialogFragment() {

    interface OnConfirmListener {

        fun onConfirmed(action: String)
    }

    companion object {

        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_MESSAGE = "KEY_MESSAGE"
        private const val KEY_BUTTON = "KEY_BUTTON"
        private const val KEY_ACTION = "KEY_ACTION"

        fun show(
            fragment: Fragment,
            title: Int,
            message: Int,
            button: Int,
            action: String
        ) {
            ConfirmDialog().apply {
                arguments = bundleOf(
                    KEY_TITLE to title,
                    KEY_MESSAGE to message,
                    KEY_BUTTON to button,
                    KEY_ACTION to action
                )
                setTargetFragment(fragment, -1)
                show(fragment.requireFragmentManager(), "ConfirmDialog")
            }
        }
    }

    private val listener: OnConfirmListener
        get() = targetFragment as OnConfirmListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(getIntValue(KEY_TITLE))
            .setMessage(getIntValue(KEY_MESSAGE))
            .setPositiveButton(getIntValue(KEY_BUTTON), null)
            .setNegativeButton(R.string.cancel, null)
            .create()
            .applyButtonListener()
    }

    private fun AlertDialog.applyButtonListener() = apply {
        setOnShowListener {
            getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                listener.onConfirmed(getAction())
                dismiss()
            }
        }
    }

    private fun getIntValue(key: String) = requireArguments().getInt(key)

    private fun getAction() = requireArguments().getString(KEY_ACTION)!!
}