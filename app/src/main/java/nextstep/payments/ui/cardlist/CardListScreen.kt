package nextstep.payments.ui.cardlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import nextstep.payments.R
import nextstep.payments.data.CardState
import nextstep.payments.data.CardState.EmptyCard
import nextstep.payments.ui.cardlist.component.CardListLazyColumn
import nextstep.payments.ui.cardlist.component.CardListTopAppBar
import nextstep.payments.ui.theme.label

@Composable
fun CardListScreen(
    isEnabledOfAddButton: Boolean,
    isEmptyOfRegisteredCards: Boolean,
    cards: ImmutableList<CardState>,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CardListTopAppBar(
                isEnabledOfAddButton = isEnabledOfAddButton,
                onAddCardClick = onAddCardClick,
            )
        },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
                .padding(horizontal = 70.dp),
        ) {
            if (isEmptyOfRegisteredCards) {
                Text(
                    text = stringResource(R.string.cardlist_text_no_card),
                    style = label,
                )
                Spacer(modifier = Modifier.height(height = 36.dp))
            }

            CardListLazyColumn(
                cards = cards,
                onAddCardClick = onAddCardClick,
            )
            Spacer(modifier = Modifier.weight(weight = 1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardListScreenPreview(
    @PreviewParameter(CardListScreenPreviewParameterProvider::class) cards: ImmutableList<CardState>,
) {
    CardListScreen(
        isEnabledOfAddButton = false,
        isEmptyOfRegisteredCards = true,
        cards = cards,
        onAddCardClick = { },
    )
}

class CardListScreenPreviewParameterProvider : PreviewParameterProvider<ImmutableList<CardState>> {
    override val values: Sequence<ImmutableList<CardState>> = sequenceOf(
        persistentListOf(EmptyCard),
        persistentListOf(
            CardState.Card(
                cardId = 0,
                cardNumber = "1234123412341234",
                expiredDate = "1221",
                ownerName = "세훈",
                password = "1234",
            ),
            EmptyCard,
        ),
        List(4) {
            CardState.Card(
                cardId = it.toLong(),
                cardNumber = "1234123412341234",
                expiredDate = "122$it",
                ownerName = "세훈$it",
                password = "1234",
            )
        }.toImmutableList(),
    )
}
