package presentation.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import common.ChangeStatusBarColors
import common.Context
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.navigation.BottomNavigation
import presentation.products.ProductScreen
import presentation.products.ProductViewModel
import presentation.products.SingleProductScreen
import presentation.productComparison.Product
import presentation.theme.DefaultCardColorsTheme
import presentation.theme.DefaultNavigationBarItemTheme
import presentation.ui.main.cart.CartNav
import presentation.ui.main.home.HomeNav
import presentation.ui.main.profile.ProfileNav
import presentation.ui.main.wishlist.WishlistNav
import presentation.ui.main.map.MapScreen
import java.net.URLEncoder
import java.net.URLDecoder
import kotlin.text.Charsets.UTF_8

@Composable
fun MainNav(context: Context, logout: () -> Unit) {
    val navBottomBarController = rememberNavController()
    val productViewModel = remember { ProductViewModel() }
    
    ChangeStatusBarColors(Color.White)
    Scaffold(bottomBar = {
        BottomNavigationUI(navController = navBottomBarController)
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                startDestination = BottomNavigation.Home.route,
                navController = navBottomBarController,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = BottomNavigation.Home.route) {
                    HomeNav(logout = logout)
                }
                composable(route = BottomNavigation.Products.route) {
                    ProductScreen(
                        viewModel = productViewModel,
                        onProductClick = { product ->
                            try {
                                val encodedName = URLEncoder.encode(product.name, UTF_8.name())
                                val encodedPrice = URLEncoder.encode(product.price, UTF_8.name())
                                val encodedImageUrl = URLEncoder.encode(product.imageUrl ?: "", UTF_8.name())
                                navBottomBarController.navigate(
                                    "single_product/$encodedName/$encodedPrice/$encodedImageUrl"
                                )
                            } catch (e: Exception) {
                                // Handle navigation error
                                println("Navigation error: ${e.message}")
                            }
                        }
                    )
                }
                composable(
                    route = "single_product/{name}/{price}/{imageUrl}",
                    arguments = listOf(
                        navArgument("name") { 
                            type = NavType.StringType
                            nullable = false
                            defaultValue = "Unknown Product"
                        },
                        navArgument("price") { 
                            type = NavType.StringType
                            nullable = false
                            defaultValue = "$0.00"
                        },
                        navArgument("imageUrl") { 
                            type = NavType.StringType
                            nullable = true
                        }
                    )
                ) { backStackEntry ->
                    try {
                        val name = URLDecoder.decode(
                            backStackEntry.arguments?.getString("name") ?: "Unknown Product", 
                            UTF_8.name()
                        )
                        val price = URLDecoder.decode(
                            backStackEntry.arguments?.getString("price") ?: "$0.00", 
                            UTF_8.name()
                        )
                        val imageUrl = backStackEntry.arguments?.getString("imageUrl")?.let {
                            URLDecoder.decode(it, UTF_8.name())
                        }

                        SingleProductScreen(
                            product = Product.fromNavArgs(name, price, imageUrl),
                            onBackClick = { navBottomBarController.popBackStack() }
                        )
                    } catch (e: Exception) {
                        // Handle decoding error
                        println("Decoding error: ${e.message}")
                        navBottomBarController.popBackStack()
                    }
                }
                composable(route = BottomNavigation.Wishlist.route) {
                    WishlistNav()
                }
                composable(route = BottomNavigation.Cart.route) {
                    CartNav(context = context)
                }
                composable(route = BottomNavigation.Profile.route) {
                    ProfileNav(context = context, logout = logout)
                }
                composable(route = BottomNavigation.Map.route) {
                    MapScreen(context = context)
                }
            }
        }
    }
}

@Composable
fun BottomNavigationUI(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Don't show bottom navigation on single product screen
    if (currentRoute?.startsWith("single_product") == true) {
        return
    }

    Card(
        colors = DefaultCardColorsTheme(),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        )
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.background,
            tonalElevation = 8.dp
        ) {
            val items = listOf(
                BottomNavigation.Home,
                BottomNavigation.Products,
                BottomNavigation.Wishlist,
                BottomNavigation.Cart,
                BottomNavigation.Profile,
                BottomNavigation.Map,
            )
            items.forEach {
                NavigationBarItem(
                    label = { Text(text = it.title) },
                    colors = DefaultNavigationBarItemTheme(),
                    selected = it.route == currentRoute,
                    icon = {
                        Icon(
                            painterResource(if (it.route == currentRoute) it.selectedIcon else it.unSelectedIcon),
                            it.title
                        )
                    },
                    onClick = {
                        if (currentRoute != it.route) {
                            navController.navigate(it.route) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }
}
