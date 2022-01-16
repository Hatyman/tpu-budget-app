package ru.tpu.budgetapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.tpu.budgetapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.itemsButton.setOnClickListener {
            navigateToScreen(BudgetItemsListActivity::class.java)
        }

        binding.plansButton.setOnClickListener {
            navigateToScreen(PlansListActivity::class.java)
        }
    }

    private fun navigateToScreen(cls: Class<*>): Unit {
        val intent = Intent(this, cls)
        startActivity(intent)
    }
 }