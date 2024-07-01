package ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.Font
import tunnelclient.composeapp.generated.resources.*
import tunnelclient.composeapp.generated.resources.Res

object Fonts {
    @Composable
    fun poppins(): FontFamily {
        return FontFamily(
            Font(Res.font.poppins_light, FontWeight.Light),
            Font(Res.font.poppins_regular, FontWeight.Normal),
            Font(Res.font.poppins_medium, FontWeight.Medium),
            Font(Res.font.poppins_bold, FontWeight.Bold),
            Font(Res.font.poppins_semi_bold, FontWeight.SemiBold),
            Font(Res.font.poppins_extra_bold, FontWeight.ExtraBold)
        )
    }
}

object Colors {
    val blue: Color = Color(0xFF105DFB)
    val green: Color = Color(0xFF1ED760)
    val red: Color = Color(0xFFE30A17)
    val yellow: Color = Color(0xFFF8981D)
    val cardBackground = Color(0x80FFFFFF)
    val black = Color(0xFF000000)
    val closerToBlack = Color(0xFF292D32)
    val almostBlack = Color(0xFF292D32)
    val white = Color(0xFFFFFFFF)
    val searchWhite = Color(0x99FFFFFF)
    val homeGray = Color(0x4DB4B4B4)
    val borderGray = Color(0xFF9B9B9B)
    val lightRed = Color(0xffF55273) // Red 300
    val darkRed = Color(0xffB70043)  // Red 800

    // Green
    val success = Color(0xFF1ED760)  // Green A700

    // Orange
    val warning = Color(0xFFF8981D)  // Orange 800
    val error = Color(0xffE53935)    // Red 600

    // Gray shades
    val darkGray = Color(0xff434656)  // Gray 800
    val lightGray = Color(0xffA7AABC) // Gray 400

}

object Padding {
    val screen = 32.dp
    val input = 10.dp
    val cards = 20.dp
}

@Composable
fun VaultTheme(content: @Composable () -> Unit) = MaterialTheme(
    colors = MaterialTheme.colors.copy(),
    typography = MaterialTheme.typography,
    shapes = Shapes(
        medium = RoundedCornerShape(10.dp),
        small = RoundedCornerShape(10.dp),
    ),
    content = content
)