package com.example.kotlintest_lib.presentation.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.kotlintest_lib.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initProfileData()
        observerProfileData()
    }

    private fun observerProfileData() {
        homeViewModel.name.observe(this) {
            binding.name.text = it
        }
        homeViewModel.email.observe(this) {
            binding.email.text = it
        }
        homeViewModel.password.observe(this) {
            binding.password.text = it
        }
        homeViewModel.role.observe(this) {
            binding.role.text = it
        }
        homeViewModel.avatar.observe(this) {
            binding.profileImage.load(it) {
                crossfade(true)
                placeholder(com.example.kotlintest_lib.R.drawable.ic_launcher_background)
                error(com.example.kotlintest_lib.R.drawable.ic_launcher_background)
            }
        }
        homeViewModel.creationAt.observe(this) {
            binding.createAt.text = it
        }
        homeViewModel.updatedAt.observe(this) {
            binding.updateAt.text = it
        }
        homeViewModel.id.observe(this) {
            binding.idUser.text = it.toString()
        }
    }

    private fun initProfileData() {
        homeViewModel.getProfile()
    }
}