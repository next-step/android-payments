package nextstep.payments.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.theme.Black100
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun BaseCard(
    modifier: Modifier = Modifier,
    color: Color = Black100,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(5.dp),
            )
            .padding(14.dp)
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun BaseCardPreview() {
    PaymentsTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(40.dp),
        ) {
            BaseCard {}
        }
    }
}
