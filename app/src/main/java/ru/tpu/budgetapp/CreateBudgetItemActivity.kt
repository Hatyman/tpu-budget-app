package ru.tpu.budgetapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import ru.tpu.budgetapp.databinding.ActivityCreateBudgetItemBinding
import ru.tpu.budgetapp.ui.UiBudgetItem
import ru.tpu.budgetapp.ui.UiCategory
import ru.tpu.budgetapp.ui.adapter.CategorySpinnerAdapter

class CreateBudgetItemActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var _binding: ActivityCreateBudgetItemBinding
    private val binding: ActivityCreateBudgetItemBinding
        get() = _binding

    private lateinit var _adapter: CategorySpinnerAdapter
    private val adapter: CategorySpinnerAdapter
        get() = _adapter

    private var selectedCategory: UiCategory? = null

    private val newItem: UiBudgetItem = UiBudgetItem()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityCreateBudgetItemBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        _adapter = CategorySpinnerAdapter(this, App.scope.categoryRepository.cachedCategories)
        binding.picker.adapter = adapter
        binding.picker.onItemSelectedListener = this
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        if (item.itemId == R.id.action_save) {
            newItem.title = binding.title.text.toString()
            newItem.category = selectedCategory as UiCategory

            if (newItem.title.isBlank() || selectedCategory == null) {
                return false
            }

            val intent = Intent()
            intent.putExtra("budgetItem", newItem)

            setResult(RESULT_OK, intent)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedCategory = adapter.getCategoryItem(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        selectedCategory = null
    }
}