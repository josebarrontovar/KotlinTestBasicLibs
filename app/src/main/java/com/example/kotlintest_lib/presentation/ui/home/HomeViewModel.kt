package com.example.kotlintest_lib.presentation.ui.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.kotlintest_lib.data.worker.ProductsWorker
import com.example.kotlintest_lib.domain.model.AuthModel
import com.example.kotlintest_lib.domain.repository.AuthRepository
import com.example.kotlintest_lib.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val dbAuthRepository: AuthRepository,
    @ApplicationContext private val applicationContext: android.content.Context,
) :
    ViewModel() {

    private val context = applicationContext

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _role = MutableLiveData<String>()
    val role: LiveData<String> get() = _role

    private val _avatar = MutableLiveData<String>()
    val avatar: LiveData<String> get() = _avatar

    private val _creationAt = MutableLiveData<String>()
    val creationAt: LiveData<String> get() = _creationAt

    private val _updatedAt = MutableLiveData<String>()
    val updatedAt: LiveData<String> get() = _updatedAt

    private val _id = MutableLiveData<Int>()
    val id: LiveData<Int> get() = _id

    private val _authLiveData = MutableLiveData<AuthModel>()
    val authLiveData: LiveData<AuthModel> = _authLiveData

    fun getProfile() {
        viewModelScope.launch {
            try {
                val dataProfile = withContext(Dispatchers.IO) {
                    profileRepository.getProfile()
                }
                _name.value = dataProfile.name
                _email.value = dataProfile.email
                _password.value = dataProfile.password
                _role.value = dataProfile.role
                _avatar.value = dataProfile.avatar
                _creationAt.value = formatDate(dataProfile.creationAt)
                _updatedAt.value = formatDate(dataProfile.updatedAt)
                _id.value = dataProfile.id

                Log.d("JGBTProfile1:", dataProfile.toString())
            } catch (e: Exception) {
                Log.d("JGBTProfile3:", "Exception: ${e.message}")
            }
        }
    }


    private fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault()
        ) // Suponiendo el formato de la fecha original
        val outputFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault()) // Formato deseado
        val date = inputFormat.parse(dateString)
        return if (date != null) outputFormat.format(date) else ""
    }

    fun getAuthFromViewModel() {
        viewModelScope.launch {
            try {
                val authEntity = withContext(Dispatchers.IO) {
                    dbAuthRepository.getAuthDB()
                }
                _authLiveData.postValue(authEntity)

            } catch (e: Exception) {
                Log.e("ViewModel", "Error retrieving auth: ${e.message}")
                _authLiveData.postValue(AuthModel("", ""))
            }
        }
    }

    fun deleteAuthById(id: Int) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    Log.d("JGBT", "0")
                    dbAuthRepository.deleteAuthById(id)
                    Log.d("JGBT", "1")
                    dbAuthRepository.resetAutoIncrement()
                    Log.d("JGBT", "2")
                }
            } catch (e: Exception) {
                Log.e("JGBT", "Error deleting auth: ${e.message}")
            }
            getAuthFromViewModel()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun startLogWorker() {
        val builder = OneTimeWorkRequest.Builder(ProductsWorker::class.java)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                Duration.ofSeconds(10)    // java.time.Duration
            )

        // Inicia el trabajo
        // 2) Construye el request
        val workRequest = builder.build()

        // 3) Encola
        WorkManager.getInstance(context)
            .enqueue(workRequest)
    }
}