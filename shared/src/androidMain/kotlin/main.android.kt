package com.razzaghi.shopingbykmp.android

import android.app.Application
import androidx.compose.runtime.Composable
import presentation.App
import presentation.productComparison.ProductComparisonScreen
import presentation.productComparison.Product
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainView(application: Application) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { /* Home Screen */ }
        composable("compare") { 
            ProductComparisonScreen(product1 = Product("Product 1", "$10"), product2 = Product("Product 2", "$20")) 
        }
    }
    App(application)
}
