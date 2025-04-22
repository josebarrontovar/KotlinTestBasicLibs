package com.example.kotlintest_lib.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import coil.load
import com.example.kotlintest_lib.databinding.ActivityHomeBinding
import com.example.kotlintest_lib.presentation.ui.products.ProductActivity
import com.example.kotlintest_lib.utils.SharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initProfileData()
        observerProfileData()
        getDataDBPrintLogs()
    }

    private fun getDataDBPrintLogs() {
        homeViewModel.authLiveData.observe(this, Observer {
            Log.d("JGBT", "getDataDBPrintLogs access: ${it.accessToken}")
            Log.d("JGBT", "getDataDBPrintLogs refresh: ${it.refreshToken}")
        })

        sharedPreferences.getString("access_token", this)?.let {
            Log.d("JGBT", "getDataSharedPrintLogs access: $it")
        }
        sharedPreferences.getString("refresh_token", this)?.let {
            Log.d("JGBT", "getDataSharedPrintLogs refresh: $it")
        }
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

        binding.followButton.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            intent.putExtra("id", binding.idUser.text.toString())
            startActivity(intent)

        }

        binding.clearToken.setOnClickListener {
            sharedPreferences.clearKey("access_token", this)
            sharedPreferences.clearKey("refresh_token", this)
            if (sharedPreferences.getString("access_token", this) == null) {
                Log.d("JGBT", "Se limpia SharedPreferences")
                homeViewModel.authLiveData.observe(this, Observer {
                    if (it.accessToken.isEmpty()) {
                        finish()
                    }
                })
            }
        }

        binding.clearDB.setOnClickListener {
            Log.d("JGBT", "Click clearDB")
            homeViewModel.deleteAuthById(1)
            Log.d("JGBT", "Click 1")
            homeViewModel.authLiveData.observe(this) { it ->
                if (it.accessToken.isEmpty()) {
                    Log.d("JGBT", "Click 2")
                    val dataShared =
                        sharedPreferences.getString("access_token", this@HomeActivity)
                    Log.d("JGBT", "accessToken ENTRO FINISH dataShared: $dataShared")
                    if (dataShared.isNullOrEmpty()) {
                        Log.d("JGBT", "ENTRO FINISH dataShared fin: $dataShared")
                        finish()
                    }
                }
            }
        }
    }

    private fun initProfileData() {
        homeViewModel.getProfile()
    }

}