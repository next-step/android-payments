package nextstep.payments.cart_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.EnrollmentPaymentCard
import nextstep.payments.PaymentListCard
import nextstep.payments.data.Card
import nextstep.payments.data.dummyDataList

@Composable
fun CardListScreen(
    cardListUiState: CardListUiState,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CardListTopAppbar(
                onAddClick = onAddClick,
                showAddTextButton = cardListUiState is CardListUiState.Many
            )
        },
        containerColor = Color.White,
    ) { innerPadding ->
        when (cardListUiState) {
            CardListUiState.Empty -> {
                CardListEmptyScreen(
                    onAddClick = onAddClick,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            is CardListUiState.One -> {
                CardListOneScreen(
                    card = cardListUiState.card,
                    onAddClick = onAddClick,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            is CardListUiState.Many -> {
                CardListManyScreen(
                    cardList = cardListUiState.cards,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun CardListEmptyScreen(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(top = 32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "새로운 카드를 등록해주세요",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.W700,
        )

        EnrollmentPaymentCard(
            modifier = Modifier.padding(top = 32.dp),
            onClick = { onAddClick() }
        )
    }
}

@Composable
fun CardListOneScreen(
    card: Card,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(top = 12.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        PaymentListCard(
            card = card
        )
        EnrollmentPaymentCard(
            onClick = { onAddClick() }
        )
    }
}

@Composable
fun CardListManyScreen(cardList: List<Card>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(cardList) {
            PaymentListCard(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardListEmptyScreenPreview() {
    CardListEmptyScreen(
        onAddClick = { },
    )
}

@Preview(showBackground = true)
@Composable
private fun CardListOneScreenPreview() {
    val card = dummyDataList.first()

    CardListOneScreen(
        card = card,
        onAddClick = { },
    )
}

@Preview(showBackground = true)
@Composable
private fun CardListManyScreenPreview() {
    val cardList = dummyDataList
    CardListManyScreen(cardList = cardList)
}