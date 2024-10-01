package nextstep.payments.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.model.Card

@Composable
fun EmptyCardState(onCardAddClicked: () -> Unit) {
    Text(
        text = stringResource(id = R.string.card_screen_card_add_information),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(32.dp)
    )
    CardList(
        cards = emptyList(),
        content = { CardAdd(onCardAddClicked = onCardAddClicked) }
    )
}

@Composable
fun OneCardState(cards: List<Card>, onCardAddClicked: () -> Unit) {
    CardList(
        cards = cards,
        content = { CardAdd(onCardAddClicked = onCardAddClicked) }
    )
}

@Composable
fun ManyCardState(cards: List<Card>) {
    CardList(cards = cards)
}

@Composable
fun CardList(
    cards: List<Card>,
    content: @Composable () -> Unit = {}
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(36.dp),
    ) {
        items(cards) { card ->
            PaymentCard(
                modifier = Modifier
                    .background(
                        color = Color(card.color),
                        shape = RoundedCornerShape(5.dp),
                    ),
                cardCompany = card.cardCompany,
                content = { CardAdd(card = card) }
            )
        }
        item {
            content()
        }
    }
}

@Composable
fun CardAdd(card: Card) {
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
                text = card.expiredDate,
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardListPreview() {
    CardList(
        cards = listOf(
            Card(
                cardNumber = "1234-0000-1111-1234",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "1234",
                color = 0xFFF04651,
                cardCompany = "BC카드"
            ),
            Card(
                cardNumber = "1234-0000-1111-1234",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "1234",
                color = 0xFF0E19ED,
                cardCompany = "신한카드"
            ),
            Card(
                cardNumber = "1234-0000-1111-1234",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "1234",
                color = 0xFFF0DE1F,
                cardCompany = "카카오뱅크"
            ),
            Card(
                cardNumber = "1234-0000-1111-1234",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "1234",
                color = 0xFF030201,
                cardCompany = "현대카드"
            ),
            Card(
                cardNumber = "1234-0000-1111-1234",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "1234",
                color = 0xFF416CE0,
                cardCompany = "우리카드"
            ),
            Card(
                cardNumber = "1234-0000-1111-1234",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "1234",
                color = 0xFFED2405,
                cardCompany = "롯데카드"
            ),
            Card(
                cardNumber = "1234-0000-1111-1234",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "1234",
                color = 0xFF0CAB73,
                cardCompany = "하나카드"
            ),
            Card(
                cardNumber = "1234-0000-1111-1234",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "1234",
                color = 0xFF695F54,
                cardCompany = "국민카드"
            ),
        )
    )
}
