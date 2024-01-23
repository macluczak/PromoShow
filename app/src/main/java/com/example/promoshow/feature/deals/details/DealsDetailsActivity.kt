package com.example.promoshow.feature.deals.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs
import com.example.promoshow.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealsDetailsActivity : AppCompatActivity() {

    val args: DealsDetailsActivityArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deals_details)

        window.statusBarColor = resources.getColor(R.color.tertiary)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_details) as NavHostFragment
        val navController = navHostFragment.navController
        navController
            .setGraph(R.navigation.details_nav, intent.extras)
    }
}