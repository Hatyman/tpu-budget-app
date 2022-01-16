package ru.tpu.budgetapp.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.tpu.budgetapp.R
import ru.tpu.budgetapp.databinding.ListviewLayoutPlanBinding
import ru.tpu.budgetapp.ui.UiPlan

class PlansAdapter(context: Context, plansList: MutableList<UiPlan> = mutableListOf()): AbstractListAdapter<UiPlan>(context, plansList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = ListviewLayoutPlanBinding.inflate(inflater, parent, false).root
        }

        val item = getItem(position)

        view.findViewById<TextView>(R.id.dates).also {
            it.text = buildDateIntervalString(item)
        }
        view.findViewById<TextView>(R.id.accuracy).also {
            it.text = context.getString(R.string.accuracy_1_d, (item.accuracy * 100).toInt()) + "%"
        }

        return view
    }

    private fun buildDateIntervalString(item: UiPlan): String {
        return "${item.startDate} - ${item.endDate}"
    }
}