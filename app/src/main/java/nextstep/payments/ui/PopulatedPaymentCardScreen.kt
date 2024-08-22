package nextstep.payments.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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