package ru.tpu.budgetapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import ru.tpu.budgetapp.databinding.ActivityListBinding
import ru.tpu.budgetapp.presenter.BudgetItemsDataType
import ru.tpu.budgetapp.presenter.BudgetItemsPresenter
import ru.tpu.budgetapp.presenter.PresenterLoadIndicationWidget
import ru.tpu.budgetapp.repository.BudgetRepository
import ru.tpu.budgetapp.ui.UiBudgetItem
import ru.tpu.budgetapp.ui.adapter.BudgetItemAdapter
import ru.tpu.budgetapp.ui.fragment.DeletionDialogFragment

class BudgetItemsListActivity : AppCompatActivity(),
    PresenterLoadIndicationWidget<BudgetItemsDataType> {
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data!!
                val item = data?.getParcelableExtra<UiBudgetItem>("budgetItem")

                if (item != null) {
                    presenter.doJobAndReloadData {
                        budgetRepository.createNewItem(item.title, item.category.id)
                    }
                }

            }
        }

    private val budgetRepository: BudgetRepository = App.scope.budgetRepository

    private lateinit var _binding: ActivityListBinding
    private val binding: ActivityListBinding
        get() = _binding

    private lateinit var _adapter: BudgetItemAdapter
    private val adapter: BudgetItemAdapter
        get() = _adapter

    private lateinit var _presenter: BudgetItemsPresenter
    private val presenter: BudgetItemsPresenter
        get() = _presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        _adapter = BudgetItemAdapter(this)
        binding.list.adapter = adapter

        binding.list.setOnItemLongClickListener { parent, view, position, id ->
            showDeleteDialog(position)
        }

        _presenter = BudgetItemsPresenter(lifecycle, this)

        binding.errorRetry.setOnClickListener {
            presenter.load()
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this, CreateBudgetItemActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        binding.indicationContainer.visibility = View.VISIBLE
        binding.loading.visibility = View.VISIBLE
        binding.noItems.visibility = View.GONE
        binding.errorContainer.visibility = View.GONE
    }

    override fun showLoaded(data: BudgetItemsDataType) {
        binding.loading.visibility = View.GONE

        if (data.isEmpty()) {
            binding.noItems.visibility = View.VISIBLE
        } else {
            binding.indicationContainer.visibility = View.GONE
        }

        adapter.setItems(data.toMutableList())

        binding.list.smoothScrollToPosition(0)
    }

    override fun showFailed() {
        binding.indicationContainer.visibility = View.VISIBLE
        binding.loading.visibility = View.GONE
        binding.errorContainer.visibility = View.VISIBLE
    }

    private fun showDeleteDialog(position: Int): Boolean {
        val func = {
            presenter.doJobAndReloadData {
                val item = adapter.getItem(position)
                budgetRepository.deleteItem(item.id)
            }
        }
        val dialog = DeletionDialogFragment(func)
        dialog.show(supportFragmentManager, "deletion-dialog")
        return true
    }

    override fun hideLoading() {
        binding.indicationContainer.visibility = View.GONE
        binding.loading.visibility = View.GONE
    }
}