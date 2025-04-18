package com.example.kotlintest_lib.presentation.ui.products

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlintest_lib.databinding.ActivityProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductActivity : AppCompatActivity() {
    val productViewModel: ProductViewModel by viewModels()
    private lateinit var binding: ActivityProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val productId = intent.getStringExtra("id")
        observersProductData()
        initProductData(productId)
    }

    private fun observersProductData() {
        productViewModel.dataProduct.observe(this) {
            Log.d("JGBT_ACTIVITY_PRODUCT", "Data Product: $it")
        }
    }

    private fun initProductData(productId: String?) {
        Log.d("JGBT11_Product", "Product ID: $productId")
        productId?.let {
            val products = productViewModel.getProducts(it)
            Log.d("JGBT22_Product", "Products: $products")
        }
    }


}