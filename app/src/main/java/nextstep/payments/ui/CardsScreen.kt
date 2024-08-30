package nextstep.payments.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.model.Card
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardsScreen(onCardAddClicked: () -> Unit, cards: List<Card>, cardAdded: Boolean) {
    Scaffold(
        topBar = { CardsTopBar() },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!cardAdded) {
                Text(
                    text = "새로운 카드를 등록해 주세요",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(32.dp)
                )
            }
            CardList(onCardAddClicked = onCardAddClicked, cards = cards)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Payments",
                fontSize = 22.sp
            )
        }
    )
}

@Composable
fun CardList(onCardAddClicked: () -> Unit, cards: List<Card>) {
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
                                text = card.expiredDate,
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            )
        }
        item {
            CardAdd(onCardAddClicked = onCardAddClicked)
        }
    }
}

@Preview
@Composable
fun CardNotExistListPreview() {
    PaymentsTheme {
        CardsScreen(
            onCardAddClicked = {},
            cards = listOf(Card("1234-5678-9012-3456", "12/34", "홍길동", "1234")),
            cardAdded = false
        )
    }
}

@Preview
@Composable
fun CardExistListPreview() {
    PaymentsTheme {
        CardsScreen(
            onCardAddClicked = {},
            cards = listOf(Card("1234-5678-9012-3456", "12/34", "홍길동", "1234")),
            cardAdded = true
        )
    }
}
