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
            ProductComparisonScreen(
                product1 = Product(
                    name = "Samsung Galaxy S23",
                    price = "$799.99",
                    imageUrl = "https://images.samsung.com/is/image/samsung/p6pim/uk/2302/gallery/uk-galaxy-s23-s911-sm-s911bzggeub-thumb-534863401"
                ),
                product2 = Product(
                    name = "iPhone 14 Pro",
                    price = "$999.99",
                    imageUrl = "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-14-pro-model-unselect-gallery-2-202209"
                )
            ) 
        }
    }
    App(application)
}
