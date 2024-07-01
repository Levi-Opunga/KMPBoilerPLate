import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import di.InitializeKoin
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    InitializeKoin().initKoin()
    ComposeViewport(document.body!!) {
        App()
    }
}