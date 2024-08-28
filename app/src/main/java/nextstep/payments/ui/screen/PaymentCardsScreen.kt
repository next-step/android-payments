package nextstep.payments.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.ui.component.AddPaymentCard
import nextstep.payments.ui.component.RegisteredPaymentCard
import nextstep.payments.ui.state.PaymentCardUiState

@Composable
fun PaymentCardsScreenRoute(
    onAddCardClick: () -> Unit,
    viewModel: PaymentCardsViewModel = viewModel(),
) {
    val uiState by viewModel.cardsScreenState.collectAsState()
    PaymentCardsScreen(
        uiState = uiState,
        onAddCardClick = onAddCardClick
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentCardsScreen(
    uiState: PaymentCardUiState,
    onAddCardClick: () -> Unit
) {
    val visible = false
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Payments",
                        fontSize = 22.sp
                    )
                },
                actions = {
                    if (visible) {
                        TextButton(onClick = { onAddCardClick() }) {
                            Text(
                                text = "추가",
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                },
            )
        }
    ) { paddingModifier ->
        PaymentCardList(
            modifier = Modifier.padding(paddingModifier),
            uiState = uiState,
            onAddCardClick = onAddCardClick,
        )
    }
}


@Composable
fun PaymentCardList(
    modifier: Modifier = Modifier,
    uiState: PaymentCardUiState,
    onAddCardClick: () -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState) {
            PaymentCardUiState.Empty -> {
                item {
                    Text(
                        modifier = modifier.fillMaxWidth(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        text = "새로운 카드를 등록해주세요",
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    AddPaymentCard(
                        onClick = onAddCardClick
                    )
                }
            }

            is PaymentCardUiState.One -> {
                item {
                    RegisteredPaymentCard()
                }
                item {
                    AddPaymentCard()
                }
            }

            is PaymentCardUiState.Many -> {
                items(uiState.cards.size) {
                    RegisteredPaymentCard()
                }
            }
        }
    }
}

@Preview
@Composable
fun PaymentCardsScreenPreview() {
    PaymentCardsScreen(
        uiState = PaymentCardUiState.Empty,
        onAddCardClick = {}
    )
}
