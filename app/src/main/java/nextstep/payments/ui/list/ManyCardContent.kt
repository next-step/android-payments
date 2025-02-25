package nextstep.payments.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.data.model.Card
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun ManyCardContent(
    cards: List<Card>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .semantics {
                contentDescription = "카드 목록"
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp),
        contentPadding = PaddingValues(top = 12.dp)
    ) {
        items(
            items = cards,
            key = { it.id }
        ) {
            PaymentCard(card = it)
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun ManyCardContentPreview() {
    PaymentsTheme {
        ManyCardContent(
            cards = listOf(
                Card(
                    number = "0000000000000000",
                    expiredDate = "0000",
                    ownerName = "홍길동",
                    password = "0000"
                ),
                Card(
                    number = "1000 - 0000 - 0000 - 0000",
                    expiredDate = "0000",
                    ownerName = "홍길동",
                    password = "0000"
                )
            )
        )
    }
}
