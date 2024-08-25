package nextstep.payments.ui.card.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.data.BcCard
import nextstep.payments.data.Card
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.ui.card.list.component.card.CardLazyColumn
import nextstep.payments.ui.card.list.component.card.CardListTopBar
import nextstep.payments.ui.card.list.component.card.EmptyCardImage
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardListScreen(
    viewModel: CardListViewModel = viewModel(),
    onAddCard: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val cards by viewModel.cardList.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { CardListTopBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            if (cards.cardList.isEmpty()) {
                CardRegistrationComment(
                    comment = "새로운 카드를 등록해주세요.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 73.dp)
                )
            } else {
                CardLazyColumn(cards)
            }
            EmptyCardImage(
                cardColor = Color(0xFFE5E5E5),
                onAddCard = onAddCard,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 73.dp,
                        end = 73.dp,
                        top = 12.dp,
                        bottom = 24.dp
                    )
                    .size(width = 208.dp, height = 124.dp)
            )
        }
    }
}

@Preview
@Composable
private fun CardListScreenExistPreview() {
    PaymentCardsRepository.addCard(
        Card(
            cardNumber = "1234-5678-1234-5678",
            ownerName = "홍길동",
            expiredDate = "12/24",
            password = "123",
            cardCompany = BcCard
        )
    )

    PaymentCardsRepository.addCard(
        Card(
            cardNumber = "1234-5678-1234-5628",
            ownerName = "홍길동",
            expiredDate = "12/24",
            password = "123",
            cardCompany = BcCard
        )
    )

    val viewModel = CardListViewModel()

    PaymentsTheme {
        CardListScreen(
            viewModel = viewModel,
            onAddCard = {}
        )
    }
}

@Preview
@Composable
private fun CardListScreenNotExistPreview() {
    PaymentCardsRepository.removeAllCard()
    PaymentsTheme {
        CardListScreen(
            onAddCard = {}
        )
    }
}
