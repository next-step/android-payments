package nextstep.payments.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

data class Typography(
    val roboto12M: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W500,
        lineHeight = 14.06.sp,
    ),
)

internal val LocalTypography = staticCompositionLocalOf { Typography() }
