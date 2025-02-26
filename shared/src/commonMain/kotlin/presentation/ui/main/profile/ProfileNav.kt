package presentation.ui.main.profile

import androidx.compose.runtime.Composable
import common.Context

@Composable
fun ProfileNav(
    context: Context,
    logout: () -> Unit
) {
    ProfileScreen(onLogout = logout)
}
