package nextstep.payments.ui.screen.creditcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.R
import nextstep.payments.ui.component.card.PaymentCard
import nextstep.payments.ui.component.card.PaymentCardLayout
import nextstep.payments.ui.component.card.PaymentCardLayoutDefaults
import nextstep.payments.ui.screen.creditcard.model.CardModel
import nextstep.payments.ui.screen.newcard.model.BankTypeModel

@Composable
fun CreditCardRoute(
    viewModel: CreditCardViewModel,
    modifier: Modifier = Modifier,
    onCardClick: (id: String) -> Unit,
    onAddClick: () -> Unit,
) {
    val state by viewModel.paymentCardsUiState.collectAsStateWithLifecycle()

    CreditCardScreen(
        modifier = modifier,
        onAddClick = onAddClick,
        onCardClick = onCardClick,
        state = state,
    )
}

@Composable
internal fun CreditCardScreen(
    state: CreditCardUiState,
    onAddClick: () -> Unit,
    onCardClick: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CreditCardTopBar(
                isShowActionButton = state.isManyCard(),
                onAddClick = onAddClick
            )
        }
    ) { innerPadding ->
        val maxScreenModifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
        when {
            state.isEmptyCard() -> {
                EmptySection(
                    onAddClick = onAddClick,
                    modifier = maxScreenModifier
                )
            }

            state.isOneCard() -> {
                OneCardSection(
                    cardModel = state.cards.first(),
                    onAddClick = onAddClick,
                    onCardClick = onCardClick,
                    modifier = maxScreenModifier
                )
            }

            state.isManyCard() -> {
                ManyCardSection(
                    cards = state.cards,
                    onCardClick = onCardClick,
                    modifier = maxScreenModifier,
                )
            }
        }
    }
}

@Composable
private fun EmptySection(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .padding(top = 32.dp)
            .semantics {
                contentDescription = context.getString(R.string.credit_card_section_zero)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.credit_card_add_new_card_title),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        AddCreditCard(
            modifier = Modifier.padding(top = 32.dp),
            onAddClick = onAddClick
        )
    }
}

@Composable
private fun OneCardSection(
    cardModel: CardModel,
    onAddClick: () -> Unit,
    onCardClick: (id: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Column(modifier = modifier.semantics {
        contentDescription = context.getString(R.string.credit_card_section_one)
    }, horizontalAlignment = Alignment.CenterHorizontally) {
        PaymentCard(
            modifier = Modifier.clickable { onCardClick(cardModel.id) },
            cardNumber = cardModel.cardNumber,
            cardOwnerName = cardModel.cardOwnerName,
            cardExpiredDate = cardModel.cardExpiredDate,
            bankType = cardModel.bankType
        )

        AddCreditCard(
            modifier = Modifier.padding(top = 32.dp),
            onAddClick = onAddClick
        )
    }
}

@Composable
private fun AddCreditCard(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit
) {
    PaymentCardLayout(
        modifier = modifier.clickable { onAddClick() },
        colors = PaymentCardLayoutDefaults.colors(containerColor = Color(0xFFE5E5E5)),
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.credit_card_add),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun ManyCardSection(
    modifier: Modifier = Modifier,
    cards: List<CardModel>,
    onCardClick: (id: String) -> Unit
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = modifier
            .padding(top = 12.dp)
            .semantics {
                contentDescription = context.getString(R.string.credit_card_section_many)
            },
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(cards) { card ->
            PaymentCard(
                modifier = Modifier.clickable { onCardClick(card.id) },
                cardNumber = card.cardNumber,
                cardOwnerName = card.cardOwnerName,
                cardExpiredDate = card.cardExpiredDate,
                bankType = card.bankType
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreditCardTopBar(
    isShowActionButton: Boolean,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(R.string.credit_card_top_bar_title))
        },
        actions = {
            if (isShowActionButton) {
                TextButton(onClick = onAddClick) {
                    Text(
                        text = stringResource(R.string.credit_card_top_bar_action_text),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun CreditCardTopBarPreview() {
    CreditCardTopBar(isShowActionButton = true) {}
}

private val previewDummyCardModel = CardModel(
    id = "",
    cardNumber = "1111222233334444",
    cardOwnerName = "이지훈",
    cardExpiredDate = "22 / 33",
    cardPassword = "",
    bankType = BankTypeModel.SHINHAN,
)

@Preview(showBackground = true, name = "카드가 0개 일때")
@Composable
fun CreditCardZeroScreenPreview() {
    CreditCardScreen(
        state = CreditCardUiState(),
        onCardClick = { },
        onAddClick = { }
    )
}

@Preview(showBackground = true, name = "카드가 1개 일때")
@Composable
fun CreditCardOneScreenPreview() {
    CreditCardScreen(
        state = CreditCardUiState(
            cards = listOf(previewDummyCardModel),
        ),
        onCardClick = { },
        onAddClick = { }
    )
}

@Preview(showBackground = true, name = "카드가 2개 이상 일때")
@Composable
fun CreditCardManyScreenPreview() {
    CreditCardScreen(
        state = CreditCardUiState(
            cards = List(5) { previewDummyCardModel }
        ),
        onCardClick = { },
        onAddClick = { }
    )
}
