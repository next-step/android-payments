package nextstep.payments.ui.cardlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.component.card.CardInformation
import nextstep.payments.ui.component.card.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun CardListManyScreen(
    cards: List<CardInformation>,
    onAddCardClick: () -> Unit,
    onCardClick: (CardInformation) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CardListTopAppBar(actions = {
                Text(
                    text = stringResource(R.string.card_list_many_screen_top_app_bar_action),
                    style = PaymentsTheme.typography.roboto18B,
                    modifier = modifier
                        .padding(end = 20.dp)
                        .clickable { onAddCardClick() }
                )
            })
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(top = 12.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(36.dp)
            ) {
                items(
                    items = cards,
                    key = { it.id }
                ) { card ->
                    PaymentCard(
                        cardInformation = card,
                        modifier = Modifier.clickable { onCardClick(card) })
                }
            }
        }
    )
}