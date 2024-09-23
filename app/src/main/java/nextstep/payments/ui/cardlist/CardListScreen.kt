package nextstep.payments.ui.cardlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R
import nextstep.payments.data.Card
import nextstep.payments.ui.cardlist.component.CardListLazyColumn
import nextstep.payments.ui.cardlist.component.CardListTopAppBar
import nextstep.payments.ui.theme.label

@Composable
fun CardListScreen(
    cards: List<Card>,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { CardListTopAppBar(onAddCardClick = onAddCardClick) },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding),
        ) {
            when (cards.isEmpty()) {
                true -> Text(
                    text = stringResource(R.string.cardlist_text_no_card),
                    style = label,
                )

                false -> CardListLazyColumn(cards)
            }
            Spacer(modifier = Modifier.weight(weight = 1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardListScreenPreview() {
    CardListScreen(
        cards = listOf(
            Card(
                cardId = 0L,
                cardNumber = "",
                expiredDate = "",
                ownerName = "",
                password = ""
            )
        ),
        onAddCardClick = { },
    )
    // 매개변수 3가지, 빈 리스트, 2개일때, 안보일때,
}
