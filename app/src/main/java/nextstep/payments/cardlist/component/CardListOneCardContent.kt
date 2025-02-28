package nextstep.payments.cardlist.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.cardlist.CardListEvent
import nextstep.payments.cardlist.CardsState
import nextstep.payments.model.Card
import nextstep.payments.ui.component.CreateCardButton
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardListOneCardContent(
    cardsStateState: CardsState.OneCard,
    sendEvent: (CardListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        item {
            PaymentCard(cardsStateState.card)
            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            CreateCardButton(
                onClick = { sendEvent(CardListEvent.OnClickCreateCardButton) },
                modifier = Modifier.testTag("CreateCardButton"),
            )
        }
    }
}

@Preview
@Composable
private fun CardListOneCardContentPreview() {
    PaymentsTheme {
        CardListOneCardContent(
            cardsStateState = CardsState.OneCard(
                Card(
                    id = 1,
                    cardNumber = "1234-5678-1234-5678",
                    expiredDate = "12/34",
                    ownerName = "홍길동",
                    password = "1234",
                )
            ),
            sendEvent = {},
        )
    }
}