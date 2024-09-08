package nextstep.payments.ui.cards.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
fun OneCardComponent(
    card: Card,
    onCardClick: (Card) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(32.dp)
            .semantics {
            contentDescription = "OneCard"
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PaymentCard(
            modifier = Modifier
                .padding(start = 52.dp, end = 52.dp)
                .clickable {
                    onCardClick(card)
                },
            card = card,
        )

        NewCard(
            modifier = Modifier.padding(top = 32.dp, start = 52.dp, end = 52.dp),
            onClick = onAddClick
        )
    }
}

@Preview
@Composable
private fun OneCardComponentPreview() {
    PaymentsTheme {
        OneCardComponent(
            card = Card(
                cardNumber = "1111-1111-1111-1111",
                expiredDate = "11 / 11",
                ownerName = "컴포즈",
                password = "1111",
                cardCompany = CardCompany.KB
            ),
            onCardClick = {},
            onAddClick = {}
        )
    }
}