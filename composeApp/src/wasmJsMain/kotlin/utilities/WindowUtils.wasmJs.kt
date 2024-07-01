package utilities

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import kotlinx.browser.window

@Composable
actual fun getScreenSizeInfo(): ScreenSizeInfo {
//     val size = js("window.screen") as org.w3c.dom.Screen
    val size = window.screen
    return remember {
        ScreenSizeInfo(
            hPX = size.height,
            wPX = size.width,
            hDP = size.height.toFloat().dp,
            wDP = size.width.toFloat().dp
        )
    }
}