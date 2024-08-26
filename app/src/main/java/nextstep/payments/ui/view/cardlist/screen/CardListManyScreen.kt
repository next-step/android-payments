package nextstep.payments.ui.view.cardlist.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.model.PaymentCardModel
import nextstep.payments.component.PaymentCard
import nextstep.payments.enums.CardCompanyCategory

@Composable
fun CardListManyScreen(
    items: List<PaymentCardModel>,
    onPaymentCardClick: (PaymentCardModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(36.dp),
    ) {
        items(items = items) { paymentCard ->
            PaymentCard(
                modifier = Modifier.clickable {
                    onPaymentCardClick(paymentCard)
                },
                cardCompanyCategory = paymentCard.cardCompanyCategory,
                cardNumber = paymentCard.cardNumber,
                expiredDate = paymentCard.expiredDate,
                ownerName = paymentCard.ownerName,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardListManyScreenPreview() {
    CardListManyScreen(
        items = List(size = 4) {
            PaymentCardModel(
                cardCompanyCategory = CardCompanyCategory.KAKAOBANK,
                cardNumber = "1234-5678-9012-3456",
                expiredDate = "12/25",
                ownerName = "SeokJun Jeong",
                password = "",
            )
        },
        onPaymentCardClick = {},
    )
}
