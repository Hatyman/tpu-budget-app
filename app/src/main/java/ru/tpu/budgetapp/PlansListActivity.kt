package ru.tpu.budgetapp

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.tpu.budgetapp.databinding.ActivityListBinding
import ru.tpu.budgetapp.presenter.PlansDataType
import ru.tpu.budgetapp.presenter.PlansPresenter
import ru.tpu.budgetapp.presenter.PresenterLoadIndicationWidget
import ru.tpu.budgetapp.repository.PlanRepository
import ru.tpu.budgetapp.ui.adapter.PlansAdapter
import ru.tpu.budgetapp.ui.fragment.DatePickerFragment
import ru.tpu.budgetapp.ui.fragment.DatePickerWidget
import ru.tpu.budgetapp.ui.fragment.DeletionDialogFragment
import java.time.LocalDate

class PlansListActivity : AppCompatActivity(), PresenterLoadIndicationWidget<PlansDataType>,
    DatePickerWidget {
    private val planRepository: PlanRepository = App.scope.planRepository

    private lateinit var _adapter: PlansAdapter
    private val adapter: PlansAdapter
        get() = _adapter

    private lateinit var _presenter: PlansPresenter
    private val presenter: PlansPresenter
        get() = _presenter

    private lateinit var _binding: ActivityListBinding
    private val binding: ActivityListBinding
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        _adapter = PlansAdapter(this)
        binding.list.adapter = adapter

        binding.list.setOnItemLongClickListener { parent, view, position, id ->
            showDeleteDialog(position)
        }

        _presenter = PlansPresenter(lifecycle, this)

        binding.errorRetry.setOnClickListener {
            presenter.load()
        }

        binding.fab.setOnClickListener {
            showDatePicker()
        }
    }


    override fun showLoading() {
        binding.indicationContainer.visibility = View.VISIBLE
        binding.loading.visibility = View.VISIBLE
        binding.noItems.visibility = View.GONE
        binding.errorContainer.visibility = View.GONE
    }

    override fun showLoaded(data: PlansDataType) {
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


    override fun hideLoading() {
        binding.indicationContainer.visibility = View.GONE
        binding.loading.visibility = View.GONE
    }

    private fun showDeleteDialog(position: Int): Boolean {
        val func = {
            presenter.doJobAndReloadData {
                val item = adapter.getItem(position)
                planRepository.deletePlan(item.id)
            }
        }
        val dialog = DeletionDialogFragment(func)
        dialog.show(supportFragmentManager, "deletion-dialog")
        return true
    }

    private fun showDatePicker() {
        DatePickerFragment(
            this,
            "Введите дату начала планирования",
            "План будет создан на месяц от выбранной даты."
        ).show(supportFragmentManager, "date-picker")
    }

    override fun onDateChange(year: Int, month: Int, dayOfMonth: Int) {
        val date = LocalDate.of(year, month + 1, dayOfMonth)
        presenter.doJobAndReloadData {
            planRepository.createNewPlan(date)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}