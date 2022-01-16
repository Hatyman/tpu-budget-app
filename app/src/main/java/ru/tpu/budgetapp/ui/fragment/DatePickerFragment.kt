package ru.tpu.budgetapp.ui.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment

class DatePickerFragment(
    private val widget: DatePickerWidget,
    var title: String?,
    var message: String?
) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return activity?.let {
            DatePickerDialog(it, this, year, month, day).also { picker ->
                if (title != null && title.isNullOrEmpty()) picker.setTitle(title)
                if (message != null && message.isNullOrEmpty()) picker.setMessage(message)
            }
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        widget.onDateChange(year, month, dayOfMonth)
    }
}