package nextstep.payments.ui.creditcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nextstep.payments.model.Card
import nextstep.payments.ui.component.PaymentCard

@Composable
internal fun OneCreditCardContent(
    card: Card,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp),
        modifier = modifier,
    ) {
        PaymentCard(
            brand = card.brand,
            cardNumber = card.cardNumber,
            ownerName = card.ownerName,
            expiredDate = card.expiredDate,
        )
        AddCreditCardBox(
            onClick = onAddCardClick,
            modifier = Modifier.size(width = 208.dp, height = 124.dp),
        )
    }
}
