package nextstep.payments.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun PaymentCardAddition(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .clip(RoundedCornerShape(5.dp))
            .clickable(onClick = onClick)
            .background(color = Color(0xFFE5E5E5)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_add),
            contentDescription = "카드 추가",
        )
    }
}

@Preview
@Composable
private fun CardAdditionPreview() {
    PaymentsTheme {
        PaymentCardAddition(onClick = {})
    }
}
