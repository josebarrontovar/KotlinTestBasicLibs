package com.example.kotlintest_lib.presentation.ui.products

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintest_lib.databinding.ActivityProductBinding
import com.example.kotlintest_lib.presentation.components.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductActivity : AppCompatActivity() {
    val productViewModel: ProductViewModel by viewModels()
    private lateinit var binding: ActivityProductBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val productId = intent.getStringExtra("id")
        observersProductData()
        initProductData(productId)
        initView()
    }

    private fun initView() {
        adapter = ProductAdapter(mutableListOf())
        binding.recyclerViewProduct.adapter = adapter

    }

    private fun observersProductData() {
        productViewModel.dataProduct.observe(this) {
            Log.d("JGBT_ACTIVITY_PRODUCT", "Data Product: $it")
            adapter.updateData(it)
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