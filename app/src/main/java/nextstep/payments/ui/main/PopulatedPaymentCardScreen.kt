package nextstep.payments.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.data.Card
import nextstep.payments.ui.component.PaymentCard

@Composable
fun PopulatedPaymentCard(
    card: Card,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        PaymentCard()
    }
}

@Preview
@Composable
private fun PopulatedPaymentCardPreview() {
    PopulatedPaymentCard(card = Card("1234-5678-1234-5678","12/23", "123", "1234567890123456"))
}