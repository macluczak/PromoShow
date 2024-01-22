package com.example.promoshow.feature.deals.previews


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.product.ProductApi
import com.example.api.product.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealsPreviewViewModel @Inject constructor(
    private val productApi: ProductApi,
) : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = productApi.getProducts()
                _products.postValue(response)
                Log.d("API_CALL", "LIST_OF_PRODUCTS: $response")

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}