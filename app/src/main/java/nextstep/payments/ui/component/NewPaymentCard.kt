package nextstep.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.ui.theme.PaymentsTheme


@Composable
internal fun NewPaymentCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = 208.dp, height = 124.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = Color(0xFFE5E5E5))
            .clickable(onClick = onClick)
            .semantics{
                contentDescription = "카드 추가"
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "+",
            fontSize = 34.sp,
            color = Color(0xFF575757)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewPaymentCardPreview() {
    PaymentsTheme {
        NewPaymentCard(onClick = {})
    }
}
