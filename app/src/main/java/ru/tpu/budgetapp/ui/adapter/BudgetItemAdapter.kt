package ru.tpu.budgetapp.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.tpu.budgetapp.R
import ru.tpu.budgetapp.databinding.ListviewLayoutBudgetItemBinding
import ru.tpu.budgetapp.ui.UiBudgetItem

class BudgetItemAdapter(context: Context, budgetItems: MutableList<UiBudgetItem> = mutableListOf()) :
    AbstractListAdapter<UiBudgetItem>(context, budgetItems) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = ListviewLayoutBudgetItemBinding.inflate(inflater, parent, false).root
        }

        val item = getItem(position)

        view.findViewById<TextView>(R.id.title).also {
            it.text = item.title
        }
        view.findViewById<TextView>(R.id.category).also {
            it.text = item.category.title
        }
        view.findViewById<View>(R.id.border).also {
            it.setBackgroundColor(
                context.resources.getColor(
                    if (item.category.isIncome) R.color.income else R.color.fee,
                    context.theme
                )
            )
        }

        return view
    }
}