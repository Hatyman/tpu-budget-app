package ru.tpu.budgetapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import ru.tpu.budgetapp.R
import ru.tpu.budgetapp.databinding.ListviewLayoutBudgetItemBinding
import ru.tpu.budgetapp.presenter.BudgetItemsDataType
import ru.tpu.budgetapp.ui.UiBudgetItem

class BudgetItemAdapter(private val context: Context, private var budgetItems: MutableList<UiBudgetItem> = mutableListOf()) :
    BaseAdapter() {
    var inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding: ListviewLayoutBudgetItemBinding

    override fun getCount(): Int {
        return budgetItems.size
    }

    override fun getItem(position: Int): UiBudgetItem {
        return budgetItems[position]
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            binding = ListviewLayoutBudgetItemBinding.inflate(inflater, parent, false)
            view = binding.root
        }

        val item = getItem(position)

        Log.d("getView", item.title)

        binding.title.text = item.title
        binding.category.text = item.category.title
        binding.border.setBackgroundColor(
            context.resources.getColor(
                if (item.category.isIncome) R.color.income else R.color.fee,
                context.theme
            )
        )

        return view
    }

    fun setItems(newItems: MutableList<UiBudgetItem>) {
        budgetItems = newItems
        notifyDataSetChanged()
    }

    fun addItem(newItem: UiBudgetItem) {
        budgetItems.add(newItem)
        notifyDataSetChanged()
    }

    fun deleteItemOnPosition(position: Int) {
        budgetItems.removeAt(position)
        notifyDataSetChanged()
    }

}