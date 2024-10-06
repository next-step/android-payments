package nextstep.payments.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import nextstep.payments.R

private val RobotoRegular =
    FontFamily(Font(resId = R.font.roboto_regular, weight = FontWeight.W400))
val RobotoMedium = FontFamily(Font(resId = R.font.roboto_medium, weight = FontWeight.W500))
private val RobotoBold = FontFamily(Font(resId = R.font.roboto_bold, weight = FontWeight.W700))

val title = TextStyle(
    fontFamily = RobotoRegular,
    fontSize = 22.sp,
    color = Color.Black,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    ),
)

val label = TextStyle(
    fontFamily = RobotoBold,
    fontSize = 18.sp,
    color = Color.Black,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    ),
)

val body = TextStyle(
    fontFamily = RobotoMedium,
    fontSize = 12.sp,
    color = Color.White,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    ),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )/* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
