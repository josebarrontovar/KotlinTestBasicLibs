package com.example.kotlintest_lib

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.kotlintest_lib.data.worker.ProductsWorker
import com.example.kotlintest_lib.domain.repository.ProductRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject  lateinit var workerFactory: WorkerFactory
    override fun onCreate() {
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG) // Opcional, para mostrar logs
            .setWorkerFactory(workerFactory)
            .build()
    }
}

// WorkerFactory to create ProductsWorker with injected dependencies
class ProductFactory @Inject constructor(private val productRepository: ProductRepository) :
    WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        // Check if the worker class is the one you want to create
        return when (workerClassName) {
            ProductsWorker::class.java.name -> {
                // Creating ProductsWorker with the injected repository
                ProductsWorker(appContext, workerParameters, productRepository)
            }
            else -> null // Return null for other workers
        }
    }
}