package com.example.kotlintest_lib.presentation.ui.login


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.kotlintest_lib.databinding.ActivityLoginBinding
import com.example.kotlintest_lib.presentation.ui.home.HomeActivity
import com.example.kotlintest_lib.utils.SharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val buttonLogin = binding.loginButton
        validateToken();
        permissions()

        buttonLogin.setOnClickListener {
            loginViewModel.validateForm()
        }

        loginViewModel.isLoginEnabled.observe(this) { isEnabled ->
            if (isEnabled) {
                loginViewModel.login(this)
            } else {
                Toast.makeText(this, "Login button is disabled", Toast.LENGTH_SHORT).show()
            }
        }

        binding.fullName.addTextChangedListener {
            loginViewModel.setFullName(it.toString())
        }
        binding.password.addTextChangedListener {
            loginViewModel.setPassword(it.toString())
        }

        loginViewModel.loginError.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        loginViewModel.isSuccessSavePreference.observe(this) {
            goToHome()
        }

    }

    private fun validateToken() {
        loginViewModel.getTokenDB().observe(this) { token ->
            if (token.accessToken.isEmpty()) {
                var tokenSharedPref =
                    sharedPreferences.getString("access_token", this@LoginActivity)
                if (tokenSharedPref != null) {
                    goToHome()
                }
            } else {
                sharedPreferences.saveString(
                    "access_token",
                    token.accessToken,
                )
                sharedPreferences.saveString(
                    "refresh_token",
                    token.refreshToken
                )
                goToHome()
            }
        }

    }

    private fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun permissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(
                arrayOf(
                    android.Manifest.permission.POST_NOTIFICATIONS,
                    android.Manifest.permission.INTERNET
                ),
                1000
            )
        } else {
            requestPermissions(
                arrayOf(
                    android.Manifest.permission.INTERNET
                ),
                1000
            )
        }
    }


}