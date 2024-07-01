package presentation.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import presentation.components.AppNavigation

class RootScreen : Screen {
    @Composable
    override fun Content() {
        AppNavigation()
    }
}