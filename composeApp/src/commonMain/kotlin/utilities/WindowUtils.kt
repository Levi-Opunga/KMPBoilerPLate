package utilities

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import kotlin.math.max
import kotlin.math.min

/** Getting screen size info for UI-related calculations */
data class ScreenSizeInfo(val hPX: Int, val wPX: Int, val hDP: Dp, val wDP: Dp)

@Composable
expect fun getScreenSizeInfo(): ScreenSizeInfo


@Composable
fun getScreenheight(): Int {
    return getScreenSizeInfo().hPX
}

@Composable
fun getScreenwidth(): Int {
    return getScreenSizeInfo().wPX
}

@Composable
fun pxToDp(px: Float): Dp {
    val density = LocalDensity.current
    return with(density) { px.toDp() }
}
@Composable
fun pxToDp(px: Int): Dp {
    val density = LocalDensity.current
    return with(density) { px.toDp() }
}


@Composable
fun dpToPx(dp: Dp) {
    val density = LocalDensity.current
    return with(density) { dp.toPx() }
}




@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun isPhone(): Boolean {
    val windowSizeClass = calculateWindowSizeClass()
    return windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact
}


@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun isTablet(): Boolean {
    val windowSizeClass = calculateWindowSizeClass()
    return windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium
}


@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun isDesktop(): Boolean {
    val windowSizeClass = calculateWindowSizeClass()
    return windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded
}


@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun isPhoneOrTablet(): Boolean {
    val windowSizeClass = calculateWindowSizeClass()
    return windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact ||
            windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium
}

@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun <T> screenAwareAssign(phoneValue: T, tabletValue: T, desktopValue: T): T {
    return when {
        isPhone() -> phoneValue
        isTablet() -> tabletValue
        isDesktop() -> desktopValue
        else -> phoneValue
    }
}


inline fun <T> toggleTwo(value: Boolean, ifTrue: T, ifFalse: T): T {
    return if (value) ifTrue else ifFalse
}



data class VisibilityInfo(
    val isVisible: Boolean,
    val visiblePercentage: Float
)

fun Modifier.visibilityChecker(
    screenSizeInfo: ScreenSizeInfo,
    onVisibilityChanged: (VisibilityInfo) -> Unit
): Modifier = this.then(
    Modifier.onGloballyPositioned { layoutCoordinates ->
        val position = layoutCoordinates.positionInRoot()
        val size = layoutCoordinates.size
        val viewportSize = IntSize(screenSizeInfo.wPX, screenSizeInfo.hPX) // Adjust according to your viewport size or screen size

        // Calculate visibility
        val isVisible = position.y + size.height > 0 && position.y < viewportSize.height
        val visibleHeight = if (isVisible) {
            val visibleTop = max(0f, position.y)
            val visibleBottom = min(viewportSize.height.toFloat(), position.y + size.height)
            visibleBottom - visibleTop
        } else {
            0
        }
        val visiblePercentage = if (size.height > 0) {
            visibleHeight.toFloat() / size.height.toFloat()
        } else {
            0f
        }

        onVisibilityChanged(VisibilityInfo(isVisible, visiblePercentage))
    }
)

fun Modifier.visibilityChecker2(screenWidth: Float, screenHeight: Float,
    onVisibilityChanged: (VisibilityInfo) -> Unit
): Modifier = this.then(
    Modifier.onGloballyPositioned { layoutCoordinates ->

        val viewportRect = Rect(0f, 0f, screenWidth, screenHeight)

        val positionInRoot = layoutCoordinates.positionInRoot()
        val size = layoutCoordinates.size
        val composableRect = Rect(
            positionInRoot.x,
            positionInRoot.y,
            positionInRoot.x + size.width,
            positionInRoot.y + size.height
        )

        val intersection = viewportRect.intersect(composableRect)

        val isVisible = intersection.width > 0 && intersection.height > 0
        val visiblePercentage = if (isVisible) {
            val visibleArea = intersection.width * intersection.height
            val totalArea = size.width * size.height
            visibleArea / totalArea
        } else {
            0f
        }

        onVisibilityChanged(VisibilityInfo(isVisible, visiblePercentage))
    }
)

