package com.example.kotlintest_lib.presentation.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.kotlintest_lib.domain.repository.LoginRepository
import com.example.kotlintest_lib.utils.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val sharedPreferences: SharedPreferences
) :
    ViewModel() {

    private val _fullName = MutableLiveData<String>()
    val fullName: LiveData<String> get() = _fullName

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _isLoginEnabled = MutableLiveData<Boolean>()
    var isLoginEnabled: LiveData<Boolean> = _isLoginEnabled

    private val _loginError = MutableLiveData<String>()
    val loginError: LiveData<String> = _loginError

    private val _isSuccessSavePreference = MutableLiveData<Boolean>()
    val isSuccessSavePreference: LiveData<Boolean> = _isSuccessSavePreference

    fun validateForm() {
        _isLoginEnabled.value = !_fullName.value.isNullOrEmpty() && !_password.value.isNullOrEmpty()
    }

    fun setFullName(name: String) {
        _fullName.value = name
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun login(context: Context) {
        val fullName = _fullName.value
        val password = _password.value

        if (!fullName.isNullOrBlank() && !password.isNullOrBlank()) {
            viewModelScope.launch {
                try {
                    val response = withContext(Dispatchers.IO) {
                        loginRepository.login(fullName, password)
                    }
                    if (response.accessToken.isNotEmpty() || response.refreshToken.isNotEmpty()) {
                        sharedPreferences.saveString(
                            "access_token",
                            response.accessToken
                        )

                        sharedPreferences.saveString(
                            "refresh_token",
                            response.refreshToken
                        )
                        _loginError.value = "Login successful"
                        _isSuccessSavePreference.value = true
                    } else {
                        _loginError.value = "Login failed"
                    }
                } catch (e: HttpException) {
                    if (e.response.code == 401) {
                        _loginError.value = "Invalid credentials"
                    } else {
                        _loginError.value = "Server error: ${e.response.code} ${e.response.message}"
                    }
                } catch (e: Exception) {
                    _loginError.value = e.message ?: "Unknown error"
                }
            }
        }
    }
}