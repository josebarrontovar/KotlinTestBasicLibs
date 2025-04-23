package com.example.kotlintest_lib.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class ProductsWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        Log.d("MILA2", "Worker executed at: ${System.currentTimeMillis()}")
        return Result.success()
    }
}