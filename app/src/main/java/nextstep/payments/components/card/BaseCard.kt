package nextstep.payments.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.domain.Card
import nextstep.payments.ui.theme.Black100
import nextstep.payments.ui.theme.PaymentsTheme

interface PaymentCard {
    val card: Card
}

class DefaultPaymentCardScope(override val card: Card) : PaymentCard

@Composable
fun BaseCard(
    card: Card,
    modifier: Modifier = Modifier,
    content: @Composable PaymentCard.() -> Unit,
) {
    val scope = remember(card) { DefaultPaymentCardScope(card) }

    Column(
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Black100,
                shape = RoundedCornerShape(5.dp),
            )
            .padding(14.dp)
    ) {
        scope.content()
    }
}

@Preview(showBackground = true)
@Composable
private fun BaseCardPreview() {
    PaymentsTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(40.dp),
        ) {
            BaseCard(
                card = Card(
                    numbers = "0000 - 0000 - 0000 - 0000",
                    expiredDate = "00 / 00",
                    ownerName = "Moon SangHyun",
                    password = "0000"
                )
            ) {

            }
        }
    }
}
