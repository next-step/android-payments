package nextstep.payments.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

data class Typography(
    val roboto12M: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W500,
        lineHeight = 14.06.sp,
    ),
    val roboto16M: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W500,
        lineHeight = 18.75.sp,
        letterSpacing = (-0.085).em
    ),
    val roboto18B: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 36.sp,
    ),
    val sans18B: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 18.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 36.sp,
    )
)

internal val LocalTypography = staticCompositionLocalOf { Typography() }
