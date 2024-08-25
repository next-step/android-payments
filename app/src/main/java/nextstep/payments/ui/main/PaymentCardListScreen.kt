package nextstep.payments.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.data.Card
import nextstep.payments.ui.state.CardUiState
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.theme.titleBoldStyle

@Composable
fun PaymentCardList(
    cardUiState: CardUiState,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            PaymentCardListTopBar {
                AddButton(
                    cardUiState = cardUiState,
                    onAddClick = onAddClick
                )
            }
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(36.dp)
        ) {
            when (cardUiState) {
                is CardUiState.Empty -> {
                    item {
                        Text(
                            text = "새로운 카드를 등록해주세요",
                            modifier = Modifier.padding(top = 32.dp),
                            style = titleBoldStyle,
                            maxLines = 1
                        )
                    }
                }

                is CardUiState.One -> {
                    item {
                        PopulatedPaymentCard(
                            card = cardUiState.card,
                            Modifier.padding(top = 12.dp)
                        )
                    }
                }

                is CardUiState.Many -> {
                    items(cardUiState.cards) { card ->
                        PopulatedPaymentCard(
                            card = card,
                            Modifier.padding(top = 12.dp)
                        )
                    }
                }
            }

            if (cardUiState !is CardUiState.Many) {
                item {
                    AddCardButton(
                        modifier = modifier,
                        onClick = onAddClick
                    )
                }
            }
        }
    }
}


@Composable
fun AddCardButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(0xFFE5E5E5),
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Card"
        )
    }
}

@Preview
@Composable
private fun PaymentCardListEmptyPreview() {
    PaymentsTheme {
        PaymentCardList(
            cardUiState = CardUiState.Empty,
            onAddClick = {}
        )
    }
}

@Preview
@Composable
private fun PaymentCardListOnePreview() {
    PaymentsTheme {
//        PaymentMain(listOf(), Modifier)
        val item = Card(
            "1234-5678-9012-3456",
            "12/34",
            "홍길동",
            "1234",
            cardCompany = "롯데카드",
            cardColor = Color.White,
        )
        PaymentCardList(
            cardUiState = CardUiState.One(item),
            onAddClick = {}
        )
    }
}

@Preview
@Composable
private fun PaymentCardListManyPreview() {
    PaymentsTheme {
        val items: List<Card> = listOf(
            Card(
                "1234-5678-9012-3456",
                "12/34",
                "홍길동",
                "1234",
                cardCompany = "롯데카드",
                cardColor = Color.White,
            ),
            Card(
                "1234-5678-9012-3456",
                "12/34",
                "홍길동",
                "1234",
                cardCompany = "롯데카드",
                cardColor = Color.White,
            )
        )
        PaymentCardList(
            cardUiState = CardUiState.Many(items),
            onAddClick = {}
        )
    }
}
