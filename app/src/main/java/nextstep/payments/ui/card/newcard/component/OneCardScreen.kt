package nextstep.payments.ui.card.newcard.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nextstep.payments.model.Card
import nextstep.payments.ui.card.list.CardListUiState
import nextstep.payments.ui.card.list.component.NewCard
import nextstep.payments.ui.component.PaymentCard

@Composable
fun OneCardScreen(
    modifier: Modifier = Modifier,
    state: CardListUiState.One,
    onShowNewCard: (Card?) -> Unit,
) {
    Column(modifier = modifier.padding(top = 12.dp)) {
        PaymentCard(card = state.card, onClick = { onShowNewCard(it) })
        NewCard(
            modifier = Modifier
                .padding(top = 32.dp)
                .width(208.dp)
                .height(124.dp),
            onClick = { onShowNewCard(null) },
        )
    }
}
