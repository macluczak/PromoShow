package com.example.promoshow

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.promoshow.databinding.ActivityMainBinding
import com.example.promoshow.feature.deals.previews.DealsViewModel
import com.example.promoshow.feature.stores.viewmodel.StoresViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val dealsViewModel: DealsViewModel by viewModels()
    val storesViewModel: StoresViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = resources.getColor(R.color.white)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)

        val swipeRefreshLayout: SwipeRefreshLayout = binding.swipeLayout

        swipeRefreshLayout.setOnRefreshListener {

            refreshData()
        }
    }

    private fun refreshData() {
        dealsViewModel.fetchProducts()
        storesViewModel.fetchStores()
        binding.swipeLayout.isRefreshing = false
    }
}