package nextstep.payments.ui.cards.component

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
import nextstep.payments.ui.common.PaymentCard

@Composable
fun OneCardComponent(
    card: Card,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.semantics {
            contentDescription = "OneCard"
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PaymentCard(
            card = card,
        )

        NewCard(
            modifier = Modifier.padding(top = 32.dp),
            onClick = onAddClick
        )
    }
}

@Preview
@Composable
private fun OneCardComponentPreview() {
    OneCardComponent(
        card = Card(
            cardNumber = "1111-1111-1111-1111",
            expiredDate = "11 / 11",
            ownerName = "컴포즈",
            password = "1111"
        ),
        onAddClick = {}
    )
}