package nextstep.payments.ui.cards.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.model.Card
import nextstep.payments.model.CardCompany
import nextstep.payments.ui.common.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun ManyCardComponent(
    cards: List<Card>,
    onCardClick: (Card) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 52.dp)
            .semantics {
                contentDescription = "manyCard"
            },
        contentPadding = PaddingValues(top = 12.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(items = cards) { card ->
            PaymentCard(
                modifier = Modifier.clickable { onCardClick(card) },
                card = card,
            )
        }
    }
}

@Preview
@Composable
private fun ManyCardComponentPreview() {
    PaymentsTheme {
        ManyCardComponent(
            cards = listOf(
                Card(id = 1,
                    cardNumber = "1111-1111-1111-1111",
                    expiredDate = "11 / 11",
                    ownerName = "컴포즈",
                    password = "1111", cardCompany = CardCompany.KB),
                Card(id = 2,
                    cardNumber = "2222-2222-2222-2222",
                    expiredDate = "22 / 22",
                    ownerName = "김컴포즈",
                    password = "2222", cardCompany = CardCompany.KAKAOBANK),
                Card(id = 3,
                    cardNumber = "3333-3333-3333-3333",
                    expiredDate = "33 / 33",
                    ownerName = "박컴포즈",
                    password = "3333", cardCompany = CardCompany.BC)
            ),
            onCardClick = {}
        )
    }
}