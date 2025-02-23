package nextstep.payments.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.data.model.Card
import nextstep.payments.ui.component.NewPaymentCard
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun OneCardContent(
    card: Card,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp),
    ) {
        PaymentCard(
            card = card,
        )
        NewPaymentCard(
            onClick = onAddCardClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OneCardContentPreview() {
    PaymentsTheme {
        OneCardContent(
            card = Card(
                number = "0000 - 0000 - 0000 - 0000",
                expiredDate = "00 / 00",
                ownerName = "홍길동",
                password = "0000"
            ),
            onAddCardClick = {}
        )
    }
}
