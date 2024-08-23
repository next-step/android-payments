package nextstep.payments.ui.view.cardlist.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.component.PaymentCardRegister
import nextstep.payments.model.PaymentCardModel
import nextstep.payments.component.PaymentCard

@Composable
fun CardListOneScreen(
    paymentCard: PaymentCardModel,
    onPaymentCardClick: (PaymentCardModel) -> Unit,
    onRegisterPaymentCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        PaymentCard(
            modifier = Modifier.clickable {
                onPaymentCardClick(paymentCard)
            },
            cardNumber = paymentCard.cardNumber,
            expiredDate = paymentCard.expiredDate,
            ownerName = paymentCard.ownerName,
        )
        PaymentCardRegister(
            onClick = onRegisterPaymentCard,
            modifier = modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardListOneScreenPreview() {
    CardListOneScreen(
        paymentCard = PaymentCardModel(
            cardNumber = "1234-5678-9012-3456",
            expiredDate = "12/25",
            ownerName = "SeokJun Jeong",
            password = "",
        ),
        onPaymentCardClick = {},
        onRegisterPaymentCard = {},
    )
}
