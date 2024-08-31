package nextstep.payments.ui.cards.component

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
    modifier: Modifier = Modifier,
    cards: List<Card>
) {
    LazyColumn(
        modifier = modifier
            .semantics {
                contentDescription = "manyCard"
            },
        contentPadding = PaddingValues(top = 12.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(items = cards) { card ->
            PaymentCard(
                card = card
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
                Card("1111-1111-1111-1111", "11 / 11", "컴포즈", "1111", cardCompany = CardCompany.NOT_SELECTED),
                Card("2222-2222-2222-2222", "22 / 22", "김컴포즈", "2222", cardCompany = CardCompany.NOT_SELECTED),
                Card("3333-3333-3333-3333", "33 / 33", "박컴포즈", "3333", cardCompany = CardCompany.NOT_SELECTED)
            ),
        )
    }
}