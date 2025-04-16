package com.example.kotlintest_lib.presentation.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlintest_lib.data.remote.ApiClient
import com.example.kotlintest_lib.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

class HomeViewModel : ViewModel() {

    private val profileRepository: ProfileRepository = ApiClient.profileRepository
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
}