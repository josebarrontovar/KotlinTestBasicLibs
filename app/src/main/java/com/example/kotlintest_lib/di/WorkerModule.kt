package com.example.kotlintest_lib.di

import androidx.work.WorkerFactory
import com.example.kotlintest_lib.ProductFactory
import com.example.kotlintest_lib.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkerModule {

    @Provides
    @Singleton
    fun provideWorkerFactory(productRepository: ProductRepository): WorkerFactory {
        return ProductFactory(productRepository)
    }
}