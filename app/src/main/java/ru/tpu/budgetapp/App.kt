package ru.tpu.budgetapp

import android.app.Application
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tpu.budgetapp.api.budget.IBudgetApi
import ru.tpu.budgetapp.api.category.ICategoryApi
import ru.tpu.budgetapp.api.plan.IPlanApi
import ru.tpu.budgetapp.repository.BudgetRepository
import ru.tpu.budgetapp.repository.CategoryRepository
import ru.tpu.budgetapp.repository.PlanRepository
import ru.tpu.budgetapp.ui.UiCategory
import kotlin.coroutines.coroutineContext

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        scope = Scope()
    }

    companion object {
        lateinit var scope: Scope
    }

    class Scope() {
        val budgetRepository: BudgetRepository
        val planRepository: PlanRepository
        val categoryRepository: CategoryRepository

        init {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.33:8080")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val budgetApi = retrofit.create(IBudgetApi::class.java)
            val planApi = retrofit.create(IPlanApi::class.java)
            val categoryApi = retrofit.create(ICategoryApi::class.java)

            budgetRepository = BudgetRepository(budgetApi)
            planRepository = PlanRepository(planApi)
            categoryRepository = CategoryRepository(categoryApi)

            GlobalScope.launch {
                categoryRepository.getAllCategories()
            }
        }
    }
}