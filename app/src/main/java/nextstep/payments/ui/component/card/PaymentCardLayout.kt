package nextstep.payments.ui.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

object PaymentCardLayoutDefaults {
    fun colors(
        containerColor: Color = Color(0xFF333333),
        contentColor: Color = Color.White
    ): PaymentCardLayoutColors = PaymentCardLayoutColors(
        containerColor = containerColor,
        contentColor = contentColor
    )
}

@Stable
data class PaymentCardLayoutColors(
    val containerColor: Color,
    val contentColor: Color
)

@Composable
fun PaymentCardLayout(
    modifier: Modifier = Modifier,
    colors: PaymentCardLayoutColors = PaymentCardLayoutDefaults.colors(),
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = colors.containerColor,
                shape = RoundedCornerShape(5.dp),
            )
            .padding(horizontal = 14.dp)
    ) {
        CompositionLocalProvider(
            LocalContentColor provides colors.contentColor
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BasicCardPreview() {
    PaymentCardLayout {}
}
