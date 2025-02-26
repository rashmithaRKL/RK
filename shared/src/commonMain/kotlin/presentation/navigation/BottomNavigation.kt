package presentation.navigation

import org.jetbrains.compose.resources.DrawableResource
import rk_shopping.shared.generated.resources.Res
import rk_shopping.shared.generated.resources.cart
import rk_shopping.shared.generated.resources.cart_border
import rk_shopping.shared.generated.resources.heart2
import rk_shopping.shared.generated.resources.heart_border2
import rk_shopping.shared.generated.resources.home
import rk_shopping.shared.generated.resources.home_border
import rk_shopping.shared.generated.resources.profile
import rk_shopping.shared.generated.resources.profile_border
import rk_shopping.shared.generated.resources.nav_map
import rk_shopping.shared.generated.resources.pc
import rk_shopping.shared.generated.resources.pc_border

sealed class BottomNavigation(
    val route: String,
    val title: String,
    val selectedIcon: DrawableResource,
    val unSelectedIcon: DrawableResource,
) {
    data object Home : BottomNavigation(
        route = "Home",
        title = "Home",
        selectedIcon = Res.drawable.home,
        unSelectedIcon = Res.drawable.home_border
    )

    data object Products : BottomNavigation(
        route = "Products",
        title = "Products",
        selectedIcon = Res.drawable.pc,
        unSelectedIcon = Res.drawable.pc_border
    )

    data object Wishlist : BottomNavigation(
        route = "Wishlist",
        title = "Wishlist",
        selectedIcon = Res.drawable.heart2,
        unSelectedIcon = Res.drawable.heart_border2
    )

    data object Cart : BottomNavigation(
        route = "Cart",
        title = "Cart",
        selectedIcon = Res.drawable.cart,
        unSelectedIcon = Res.drawable.cart_border
    )

    data object Profile : BottomNavigation(
        route = "Profile",
        title = "Profile",
        selectedIcon = Res.drawable.profile,
        unSelectedIcon = Res.drawable.profile_border
    )

    data object Map : BottomNavigation(
        route = "Map",
        title = "Map",
        selectedIcon = Res.drawable.nav_map,
        unSelectedIcon = Res.drawable.nav_map
    )
}
