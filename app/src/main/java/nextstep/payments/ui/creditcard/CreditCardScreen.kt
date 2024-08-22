package nextstep.payments.ui.creditcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.model.Brand
import nextstep.payments.model.Card
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.component.PaymentsTopBar
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun CreditCardRoute(
    modifier: Modifier = Modifier,
    onAddCardClick: () -> Unit,
    viewModel: CreditCardViewModel =
        viewModel(
            factory = CreditCardViewModelFactory(LocalSavedStateRegistryOwner.current),
        ),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.shouldFetchCards) {
        viewModel.shouldFetchCards.collect {
            viewModel.fetchCards()
        }
    }

    CreditCardScreen(
        uiState = uiState,
        onAddCardClick = onAddCardClick,
        modifier = modifier,
    )
}

@Composable
internal fun CreditCardScreen(
    uiState: CreditCardUiState,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            PaymentsTopBar(
                title = stringResource(id = R.string.title_credit_card),
                titleTextAlign = TextAlign.Center,
                actions = {
                    if (uiState is CreditCardUiState.Many) {
                        TextButton(onClick = onAddCardClick) {
                            Text(
                                text = stringResource(id = R.string.tob_bar_action_add),
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                            )
                        }
                    }
                },
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        when (uiState) {
            is CreditCardUiState.Empty -> {
                EmptyCreditCardContent(
                    onAddCardClick = onAddCardClick,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(top = 32.dp, start = 73.dp, end = 73.dp),
                )
            }

            is CreditCardUiState.One -> {
                OneCreditCardContent(
                    card = uiState.card,
                    onAddCardClick = onAddCardClick,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(top = 32.dp, start = 73.dp, end = 73.dp),
                )
            }

            is CreditCardUiState.Many -> {
                ManyCreditCardContent(
                    cards = uiState.cards,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                )
            }
        }
    }
}

@Composable
private fun EmptyCreditCardContent(
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = modifier,
    ) {
        Text(
            text = stringResource(id = R.string.cretdit_card_empty_title),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        AddCreditCardBox(
            onClick = onAddCardClick,
            modifier = Modifier.size(width = 208.dp, height = 124.dp),
        )
    }
}

@Composable
private fun OneCreditCardContent(
    card: Card,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp),
        modifier = modifier,
    ) {
        PaymentCard(
            brand = card.brand,
            cardNumber = card.cardNumber,
            ownerName = card.ownerName,
            expiredDate = card.expiredDate,
        )
        AddCreditCardBox(
            onClick = onAddCardClick,
            modifier = Modifier.size(width = 208.dp, height = 124.dp),
        )
    }
}

@Composable
private fun ManyCreditCardContent(
    cards: List<Card>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
        contentPadding =
            PaddingValues(
                vertical = 12.dp,
                horizontal = 76.dp,
            ),
        modifier = modifier,
    ) {
        items(cards) { card ->
            PaymentCard(
                brand = card.brand,
                cardNumber = card.cardNumber,
                ownerName = card.ownerName,
                expiredDate = card.expiredDate,
            )
        }
    }
}

@Preview
@Composable
private fun CreditCardScreenPreview(
    @PreviewParameter(CreditCardProvider::class) uiState: CreditCardUiState,
) {
    PaymentsTheme {
        CreditCardScreen(
            uiState = uiState,
            onAddCardClick = {},
        )
    }
}

private class CreditCardProvider : PreviewParameterProvider<CreditCardUiState> {
    override val values: Sequence<CreditCardUiState>
        get() =
            sequenceOf(
                CreditCardUiState.Empty,
                CreditCardUiState.One(
                    Card(
                        brand = Brand.BC,
                        cardNumber = "1234567812345678",
                        ownerName = "홍길동",
                        expiredDate = "1234",
                        password = "1234",
                    ),
                ),
                CreditCardUiState.Many(
                    listOf(
                        Card(
                            brand = Brand.BC,
                            cardNumber = "1234567812345678",
                            ownerName = "홍길동",
                            expiredDate = "1234",
                            password = "1234",
                        ),
                        Card(
                            brand = Brand.SHINHAN,
                            cardNumber = "1234567812345678",
                            ownerName = "홍길동",
                            expiredDate = "1234",
                            password = "1234",
                        ),
                        Card(
                            brand = Brand.KAKAO_BANK,
                            cardNumber = "1234567812345678",
                            ownerName = "홍길동",
                            expiredDate = "1234",
                            password = "1234",
                        ),
                    ),
                ),
            )
}
