package nextstep.payments.ui.card.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.data.BankType
import nextstep.payments.data.Card
import nextstep.payments.data.RegisteredCreditCards
import nextstep.payments.ui.card.CreditCardUiState
import nextstep.payments.ui.card.list.component.card.CardLazyColumn
import nextstep.payments.ui.card.list.component.card.CardListTopBar
import nextstep.payments.ui.card.list.component.card.CardListTopBarWithAdd
import nextstep.payments.ui.card.list.component.card.EmptyCardImage
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardListScreen(
    viewModel: CardListViewModel = viewModel(),
    onAddCard: () -> Unit = {},
) {
    val cards by viewModel.registeredCreditCards.collectAsStateWithLifecycle()

    CardListScreen(
        registeredCreditCards = cards,
        onAddCard = onAddCard,
    )
}

@Composable
fun CardListScreen(
    registeredCreditCards: RegisteredCreditCards = RegisteredCreditCards(mutableListOf()),
    onAddCard: () -> Unit = {}
) {

    when (registeredCreditCards.getState()) {
        is CreditCardUiState.Empty -> {
            CardListScreenEmpty(
                comment = stringResource(id = R.string.text_card_registration_comment),
                onAddCard = onAddCard,
            )
        }

        is CreditCardUiState.One -> {
            CardListScreenOne(
                cards = registeredCreditCards.cardList,
                onAddCard = onAddCard,
            )
        }

        is CreditCardUiState.Many -> {
            CardListScreenMany(
                cards = registeredCreditCards.cardList,
                onAddCard = onAddCard,
            )
        }
    }
}

@Composable
fun CardListScreenEmpty(
    comment: String,
    onAddCard: () -> Unit = {}
) {
    Scaffold(topBar = { CardListTopBar() }) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Text(
                text = comment,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 73.dp)
                    .testTag(stringResource(id = R.string.test_tag_comment))
            )

            EmptyCardImage(
                cardColor = Color(0xFFE5E5E5),
                onAddCard = onAddCard,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 73.dp, end = 73.dp, top = 12.dp, bottom = 24.dp
                    )
                    .size(width = 208.dp, height = 124.dp)
            )
        }
    }
}

@Composable
fun CardListScreenOne(
    cards: List<Card>,
    onAddCard: () -> Unit = {}
) {
    Scaffold(topBar = { CardListTopBar() }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            CardLazyColumn(
                cards = cards,
                modifier = Modifier.align(CenterHorizontally)
            )

            EmptyCardImage(
                cardColor = Color(0xFFE5E5E5),
                onAddCard = onAddCard,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(
                        start = 73.dp, end = 73.dp, top = 12.dp, bottom = 24.dp
                    )
                    .size(width = 208.dp, height = 124.dp)
            )
        }
    }
}

@Composable
fun CardListScreenMany(
    cards: List<Card>,
    onAddCard: () -> Unit = {}
) {
    Scaffold(topBar = {
        CardListTopBarWithAdd(
            onClickAdd = onAddCard
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            CardLazyColumn(
                cards = cards,
                modifier = Modifier.align(CenterHorizontally)
            )
        }
    }
}

@Preview
@Composable
private fun CardListScreenEmptyPreview() {
    PaymentsTheme {
        CardListScreen(
            registeredCreditCards = RegisteredCreditCards(mutableListOf()),
            onAddCard = {}
        )
    }
}

@Preview
@Composable
private fun CardListScreenOnePreview() {
    PaymentsTheme {
        CardListScreen(
            registeredCreditCards = RegisteredCreditCards(
                cardList = listOf(
                    Card(
                        cardNumber = "1234-5678-1234-6654",
                        ownerName = "홍길동",
                        expiredDate = "12/24",
                        password = "123",
                        brandColor = colorResource(id = BankType.BC.brandColor)
                    )
                )
            ),
            onAddCard = {}
        )
    }
}

@Preview
@Composable
private fun CardListScreenManyPreview() {
    val registeredCreditCards = RegisteredCreditCards(
        cardList = listOf(
            Card(
                cardNumber = "1234-5678-1234-6654",
                ownerName = "홍길동",
                expiredDate = "12/24",
                password = "123",
                brandColor = colorResource(id = BankType.BC.brandColor)
            ),
            Card(
                cardNumber = "1234-5678-1234-1234",
                ownerName = "홍길동",
                expiredDate = "12/24",
                password = "123",
                brandColor = colorResource(id = BankType.KAKAO.brandColor)
            )
        )
    )

    PaymentsTheme {
        CardListScreen(registeredCreditCards = registeredCreditCards, onAddCard = {})
    }
}


