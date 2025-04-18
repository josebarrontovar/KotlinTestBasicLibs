package com.example.kotlintest_lib.presentation.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlintest_lib.data.remote.ApiClient
import com.example.kotlintest_lib.domain.model.Product
import com.example.kotlintest_lib.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductViewModel : ViewModel() {


    private val _dataProduct = MutableLiveData<List<Product>>()
    val dataProduct: LiveData<List<Product>> get() = _dataProduct

    private val productRepository: ProductRepository = ApiClient.productRepository
    fun getProducts(id: String): List<Product> {
        viewModelScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    productRepository.getProductList(id)
                }
                _dataProduct.value = data

            } catch (e: Exception) {

            }
        }
        return emptyList()
    }
}