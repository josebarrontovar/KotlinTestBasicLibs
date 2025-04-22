package com.example.kotlintest_lib.di

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.kotlintest_lib.data.database.AppDatabase
import com.example.kotlintest_lib.data.database.dao.AuthDao
import com.example.kotlintest_lib.data.remote.ApiService
import com.example.kotlintest_lib.data.remote.TokenInterceptor
import com.example.kotlintest_lib.domain.repository.AuthRepository
import com.example.kotlintest_lib.domain.repository.AuthRepositoryImpl
import com.example.kotlintest_lib.domain.repository.LoginRepository
import com.example.kotlintest_lib.domain.repository.LoginRepositoryImpl
import com.example.kotlintest_lib.domain.repository.ProductRepository
import com.example.kotlintest_lib.domain.repository.ProductRepositoryImpl
import com.example.kotlintest_lib.domain.repository.ProfileRepository
import com.example.kotlintest_lib.domain.repository.ProfileRepositoryImpl
import com.example.kotlintest_lib.utils.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(apiService: ApiService): ProductRepository {
        return ProductRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(apiService: ApiService): ProfileRepository {
        return ProfileRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(apiService: ApiService): LoginRepository {
        return LoginRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context, db: AuthDao): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(context, db))
            .addInterceptor(
                ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector(context))
                    .maxContentLength(250000L)
                    .alwaysReadResponseBody(true)
                    .build()
            )
            .build()
    }

    @Provides
    fun provideBaseUrl(): String = "https://api.escuelajs.co/api/v1/"

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return SharedPreferences(context)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database",
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideAuthDao(database: AppDatabase): AuthDao {
        return database.authDao()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(dbAuth: AuthDao): AuthRepository {
        return AuthRepositoryImpl(dbAuth)
    }
}