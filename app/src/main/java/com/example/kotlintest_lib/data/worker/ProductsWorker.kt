package com.example.kotlintest_lib.data.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.kotlintest_lib.domain.repository.ProductRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ProductsWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val productsRepository: ProductRepository,
) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        Log.d("MILA2", "Worker executed at: ${System.currentTimeMillis()}")
        // Simulate a long-running task
        val productList = productsRepository.getProductList("1")
        Log.d("MILA2", "Product list: $productList")

        return Result.success()
    }
}