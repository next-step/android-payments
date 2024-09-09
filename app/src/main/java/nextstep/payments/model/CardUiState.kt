package nextstep.payments.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.ui.CardAdd
import nextstep.payments.ui.PaymentCard
import java.time.format.DateTimeFormatter

sealed interface CardUiState {
    @Composable
    fun Render(cards: List<Card>, onCardAddClicked: () -> Unit)

    data object Empty : CardUiState {
        @Composable
        override fun Render(cards: List<Card>, onCardAddClicked: () -> Unit) {
            Text(
                text = stringResource(id = R.string.card_screen_card_add_information),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(32.dp)
            )
            CardList(
                cards = cards,
                content = { CardAdd(onCardAddClicked = onCardAddClicked) }
            )
        }
    }

    data object One : CardUiState {
        @Composable
        override fun Render(cards: List<Card>, onCardAddClicked: () -> Unit) {
            CardList(
                cards = cards,
                content = { CardAdd(onCardAddClicked = onCardAddClicked) }
            )
        }
    }

    data object Many : CardUiState {
        @Composable
        override fun Render(cards: List<Card>, onCardAddClicked: () -> Unit) {
            CardList(cards = cards)
        }
    }
}

@Composable
fun CardList(
    cards: List<Card>,
    content: @Composable () -> Unit = {}
) {
    val formatter = DateTimeFormatter.ofPattern("MM/yy")

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(36.dp),
    ) {
        itemsIndexed(cards) { _, card ->
            PaymentCard(
                content = {
                    Column(
                        modifier = Modifier
                            .padding(start = 14.dp, end = 14.dp)
                            .fillMaxWidth(),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = card.cardNumber,
                                color = Color.White,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                textAlign = TextAlign.Start,
                                style = TextStyle(letterSpacing = 3.sp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,

                            ) {
                            Text(
                                text = card.ownerName,
                                color = Color.White,
                                fontSize = 12.sp
                            )
                            Text(
                                text = card.expiredDate.format(formatter),
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            )
        }
        item {
            content()
        }
    }
}
