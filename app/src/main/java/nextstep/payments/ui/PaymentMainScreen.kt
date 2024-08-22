package nextstep.payments.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.data.Card
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.theme.titleBoldStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMain(
    cards: List<Card>,
    modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Payments")
            },
            modifier = Modifier.fillMaxWidth())
             },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(36.dp)
        ) {
            if (cards.isEmpty()) {
                item {
                    Text(
                        text = "새로운 카드를 등록해주세요",
                        modifier = Modifier.padding(top = 32.dp),
                        style = titleBoldStyle,
                        maxLines = 1)
                }
            } else {
                items(cards) { card ->
                    PopulatedPaymentCard(card = card, Modifier.padding(top = 12.dp))
                }
            }

            item {
                AddCardButton(Modifier) {

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
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(0xFFE5E5E5),
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .padding(16.dp)
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Card"
            )
        }
    }
}

@Preview
@Composable
private fun PaymentMainPreview() {
    PaymentsTheme {
//        PaymentMain(listOf(), Modifier)
        PaymentMain(listOf(Card("1234-5678-9012-3456", "12/34", "홍길동", "1234")), Modifier)
    }
}