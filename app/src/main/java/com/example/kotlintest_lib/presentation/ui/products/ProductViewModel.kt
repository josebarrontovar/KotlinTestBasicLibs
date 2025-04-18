package com.example.kotlintest_lib.presentation.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlintest_lib.domain.model.Product
import com.example.kotlintest_lib.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {


    private val _dataProduct = MutableLiveData<List<Product>>()
    val dataProduct: LiveData<List<Product>> get() = _dataProduct

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