package com.example.promoshow.feature.category.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.promoshow.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_search) as NavHostFragment
        val navController = navHostFragment.navController
        navController
            .setGraph(R.navigation.category_nav, intent.extras)
    }
}