package com.example.kotlintest_lib.presentation.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlintest_lib.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initProfileData()
    }

    private fun initProfileData() {
        homeViewModel.getProfile()
    }
}