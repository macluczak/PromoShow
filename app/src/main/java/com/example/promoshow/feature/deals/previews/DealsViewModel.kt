package com.example.promoshow.feature.deals.previews


import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.product.ProductApi
import com.example.api.product.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealsViewModel @Inject constructor(
    private val productApi: ProductApi,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = productApi.getProducts()
                _products.postValue(response)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("favourites_prefs", Context.MODE_PRIVATE)

    private val FAVOURITES_KEY = "favourites_key"

    fun addToFavourites(productIds: List<String>) {
        val currentFavourites = getFavourites()
        val updatedFavourites = currentFavourites.toMutableSet().apply {
            addAll(productIds)
        }
        saveFavourites(updatedFavourites)
    }

    fun removeFromFavourites(productIds: List<String>) {
        val currentFavourites = getFavourites()
        val updatedFavourites = currentFavourites.toMutableSet().apply {
            removeAll(productIds.toSet())
        }
        saveFavourites(updatedFavourites)
    }

    fun getFavourites(): Set<String> {
        return sharedPreferences.getStringSet(FAVOURITES_KEY, setOf()) ?: setOf()
    }

    fun isFavourite(productId: String): Boolean {
        return getFavourites().contains(productId)
    }

    fun clearFavourites() {
        saveFavourites(emptySet())
    }

    fun updateFavourite(productId: String, isFavourite: Boolean) {
        val currentFavourites = getFavourites().toMutableSet()

        if (isFavourite) {
            currentFavourites.add(productId)
        } else {
            currentFavourites.remove(productId)
        }

        saveFavourites(currentFavourites)
    }

    private fun saveFavourites(favourites: Set<String>) {
        sharedPreferences.edit().putStringSet(FAVOURITES_KEY, favourites).apply()
    }
}