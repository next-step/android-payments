package nextstep.payments.ui.creditcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.model.Brand
import nextstep.payments.model.Card
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun ManyCreditCardContent(
    cards: List<Card>,
    onCardClick: (Card) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
        contentPadding =
            PaddingValues(
                vertical = 12.dp,
                horizontal = 76.dp,
            ),
        modifier = modifier,
    ) {
        items(cards) { card ->
            PaymentCard(
                brand = card.brand,
                cardNumber = card.cardNumber,
                ownerName = card.ownerName,
                expiredDate = card.expiredDate,
                onClick = { onCardClick(card) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ManyCreditCardContentPreview() {
    PaymentsTheme {
        ManyCreditCardContent(
            cards =
                listOf(
                    Card(
                        brand = Brand.BC,
                        cardNumber = "1234-5678-1234-5678",
                        ownerName = "홍길동",
                        expiredDate = "12/34",
                        password = "1234",
                    ),
                    Card(
                        brand = Brand.SHINHAN,
                        cardNumber = "1234-5678-1234-5678",
                        ownerName = "홍길동",
                        expiredDate = "12/34",
                        password = "1234",
                    ),
                    Card(
                        brand = Brand.KAKAO_BANK,
                        cardNumber = "1234-5678-1234-5678",
                        ownerName = "홍길동",
                        expiredDate = "12/34",
                        password = "1234",
                    ),
                ),
            onCardClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
