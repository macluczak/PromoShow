package com.example.promoshow.feature.stores.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs
import com.example.promoshow.R

class StoresDetailsActivity : AppCompatActivity() {

    val args: StoresDetailsActivityArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stores_details)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_stores) as NavHostFragment
        val navController = navHostFragment.navController
        navController
            .setGraph(R.navigation.stores_nav, intent.extras)
    }
}