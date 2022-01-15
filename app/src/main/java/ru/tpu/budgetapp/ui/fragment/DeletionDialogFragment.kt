package ru.tpu.budgetapp.ui.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.coroutines.Job
import ru.tpu.budgetapp.R

class DeletionDialogFragment(
    private val deleteItem: () -> Job
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setTitle(R.string.delete_title_dialog)
                .setMessage(R.string.delete_message_dialog).setPositiveButton(
                R.string.delete_button_dialog,
                DialogInterface.OnClickListener { dialog, id ->
                    deleteItem()
                })
                .setNegativeButton(R.string.cancel_button_dialog,
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}