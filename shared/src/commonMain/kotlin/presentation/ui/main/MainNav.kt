package presentation.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.*
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
import business.cart.CartManager
import business.review.ReviewManager
import java.net.URLEncoder
import java.net.URLDecoder
import kotlin.text.Charsets.UTF_8

@Composable
fun MainNav(
    context: Context,
    logout: () -> Unit = {}
) {
    val navBottomBarController = rememberNavController()
    val productViewModel = remember { ProductViewModel() }
    val cartManager = remember { CartManager() }
    val reviewManager = remember { ReviewManager() }
    
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
                    HomeNav(
                        onNavigateToNotifications = {
                            // Handle notifications navigation
                        },
                        onNavigateToCategories = {
                            navBottomBarController.navigate(BottomNavigation.Products.route)
                        }
                    )
                }
                composable(route = BottomNavigation.Products.route) {
                    ProductScreen(
                        viewModel = productViewModel,
                        onProductClick = { product ->
                            val encodedName = URLEncoder.encode(product.name, UTF_8.name())
                            val encodedPrice = URLEncoder.encode(product.price, UTF_8.name())
                            val encodedImageUrl = URLEncoder.encode(product.imageUrl ?: "", UTF_8.name())
                            val route = "single_product/$encodedName/$encodedPrice/$encodedImageUrl"
                            navBottomBarController.navigate(route)
                        }
                    )
                }
                composable(
                    route = "single_product/{name}/{price}/{imageUrl}",
                    arguments = listOf(
                        navArgument("name") { 
                            type = NavType.StringType
                            defaultValue = "Unknown Product"
                        },
                        navArgument("price") { 
                            type = NavType.StringType
                            defaultValue = "$0.00"
                        },
                        navArgument("imageUrl") { 
                            type = NavType.StringType
                            defaultValue = ""
                        }
                    )
                ) { backStackEntry ->
                    val name = URLDecoder.decode(backStackEntry.arguments?.getString("name"), UTF_8.name())
                    val price = URLDecoder.decode(backStackEntry.arguments?.getString("price"), UTF_8.name())
                    val imageUrl = URLDecoder.decode(backStackEntry.arguments?.getString("imageUrl"), UTF_8.name())
                        .takeIf { it.isNotBlank() }
                    
                    SingleProductScreen(
                        product = Product.fromNavArgs(name, price, imageUrl),
                        cartManager = cartManager,
                        reviewManager = reviewManager,
                        onBackClick = { navBottomBarController.popBackStack() }
                    )
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

@OptIn(ExperimentalResourceApi::class)
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
