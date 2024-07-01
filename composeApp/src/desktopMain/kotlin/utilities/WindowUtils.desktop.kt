package utilities

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp

@Composable
actual fun getScreenSizeInfo(): ScreenSizeInfo {
    val size = java.awt.Toolkit.getDefaultToolkit().screenSize
    return remember{ ScreenSizeInfo(
        hPX = size.height,
        wPX = size.width,
        hDP = size.height.toFloat().dp,
        wDP = size.width.toFloat().dp
    )}
}