package presentation.ui.main.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavGraphBuilder.homeNav(
    navController: NavHostController,
    onNavigateToNotifications: () -> Unit,
    onNavigateToCategories: () -> Unit,
    onNavigateToProduct: (String) -> Unit
) {
    composable(route = "home") {
        HomeScreen(
            onNotificationClick = onNavigateToNotifications,
            onCategoryClick = onNavigateToCategories,
            onProductClick = onNavigateToProduct
        )
    }
}

@Composable
fun HomeNav(
    onNavigateToNotifications: () -> Unit = {},
    onNavigateToCategories: () -> Unit = {},
    onNavigateToProduct: (String) -> Unit = {}
) {
    HomeScreen(
        onNotificationClick = onNavigateToNotifications,
        onCategoryClick = onNavigateToCategories,
        onProductClick = onNavigateToProduct
    )
}
