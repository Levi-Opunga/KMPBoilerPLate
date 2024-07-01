package presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ui.Colors
import utilities.isDesktop
import utilities.screenAwareAssign
import utilities.toggleTwo

@Composable
fun DotsRectangle(number: Int, active:Int, activeColor: Color = Colors.almostBlack, inactiveColor: Color = Colors.white, borderColor: Color = Colors.almostBlack, size: Int = 105, roundedSize: Int = 47, spacing: Int = 20,  width: Int = 105, height: Int = 11, borderSize: Int = 1, modifier: Modifier = Modifier.fillMaxWidth()) {
   var calculatedWith = if (isDesktop()) 2 * width else width
    Row(
        horizontalArrangement = toggleTwo(
            isDesktop(),
            Arrangement.SpaceEvenly,
            Arrangement.spacedBy(spacing.dp, Alignment.CenterHorizontally)
        ),
        modifier = modifier
    ) {

        for (i in 0 until number) {
            Box(
                modifier = Modifier
                    .border(width = borderSize.dp, color = if (i <= active-1) activeColor else borderColor, shape = RoundedCornerShape(size = roundedSize.dp))
                    .width(calculatedWith.dp)
                    .height(height.dp)
                    .background(color = if (i <= active-1) activeColor else inactiveColor, shape = RoundedCornerShape(size = roundedSize.dp))
            )
        }

    }
}

@Composable
fun DotsCircle(number: Int, active:Int, activeColor: Color = Colors.almostBlack, inactiveColor: Color = Colors.white, borderColor: Color = Colors.almostBlack, size: Int = 14, spacing: Int = 8, borderSize: Int = 1, modifier: Modifier = Modifier.fillMaxWidth()) {
    Row(
        horizontalArrangement = toggleTwo(
            isDesktop(),
            Arrangement.SpaceEvenly,
            Arrangement.spacedBy(spacing.dp, Alignment.CenterHorizontally)
        ),
        modifier = modifier
    ) {

        for (i in 0 until number) {
            Box(
                modifier = Modifier
                    .border(width = borderSize.dp, color = if (i <= active-1) activeColor else borderColor, shape = RoundedCornerShape(size = size.dp))
                    .width(size.dp)
                    .height(size.dp)
                    .background(color = if (i <= active-1) activeColor else inactiveColor, shape = RoundedCornerShape(size = size.dp))
            )
        }

    }
}