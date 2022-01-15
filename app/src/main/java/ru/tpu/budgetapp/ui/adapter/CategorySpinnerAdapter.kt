package ru.tpu.budgetapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import ru.tpu.budgetapp.R
import ru.tpu.budgetapp.databinding.ListviewLayoutBudgetItemBinding
import ru.tpu.budgetapp.databinding.SpinnerLayoutCategoryBinding
import ru.tpu.budgetapp.ui.UiCategory

class CategorySpinnerAdapter(
    context: Context, private var categories: List<UiCategory> = listOf()
) : ArrayAdapter<String>(
    context,
    android.R.layout.simple_spinner_item,
    categories.map { uiCategory -> uiCategory.title }) {
    var inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding: SpinnerLayoutCategoryBinding

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            binding = SpinnerLayoutCategoryBinding.inflate(inflater, parent, false)
            view = binding.root
        }

        val category = getCategoryItem(position)

        binding.text.text = category.title
        binding.border.setBackgroundColor(
            context.resources.getColor(
                if (category.isIncome) R.color.income else R.color.fee,
                context.theme
            )
        )

        return view
    }

    fun getCategoryItem(position: Int): UiCategory {
        return categories[position]
    }
}