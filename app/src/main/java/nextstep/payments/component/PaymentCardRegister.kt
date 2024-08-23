package nextstep.payments.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.consts.CardDefaults

@Composable
fun PaymentCardRegister(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .shadow(8.dp)
            .size(size = CardDefaults.CARD_SIZE)
            .clip(RoundedCornerShape(5.dp))
            .background(color = Color(0xFFE5E5E5))
            .clickable(onClick = onClick)
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            imageVector = Icons.Filled.Add,
            contentDescription = "카드 추가 아이콘"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardRegisterPreview() {
    PaymentCardRegister(onClick = {})
}
