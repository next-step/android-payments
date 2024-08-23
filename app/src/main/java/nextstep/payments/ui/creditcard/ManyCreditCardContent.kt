package nextstep.payments.ui.creditcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nextstep.payments.model.Card
import nextstep.payments.ui.component.PaymentCard

@Composable
internal fun ManyCreditCardContent(
    cards: List<Card>,
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
            )
        }
    }
}
