package com.example.kotlintest_lib.presentation.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlintest_lib.data.remote.ApiClient
import com.example.kotlintest_lib.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    private val profileRepository: ProfileRepository = ApiClient.profileRepository


    fun getProfile() {
        viewModelScope.launch {
            try {
                val dataProfile = withContext(Dispatchers.IO) {
                    profileRepository.getProfile()
                }
                Log.d("JGBTProfile1:", dataProfile.toString())
            } catch (e: Exception) {
                Log.d("JGBTProfile3:", "Exception: ${e.message}")
            }
        }

    }
}