import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.InitializeKoin

fun main() = application {
    InitializeKoin().initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "TunnelClient",
    ) {
        App()
    }
}