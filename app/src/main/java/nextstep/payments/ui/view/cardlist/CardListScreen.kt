package nextstep.payments.ui.view.cardlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.model.PaymentCardModel
import nextstep.payments.ui.view.cardlist.screen.CardListEmptyScreen
import nextstep.payments.ui.view.cardlist.screen.CardListManyScreen
import nextstep.payments.ui.view.cardlist.screen.CardListOneScreen

@Composable
fun CardListScreen(
    onShowPaymentCardDetail: (PaymentCardModel) -> Unit,
    onRegisterPaymentCard: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CardListViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CardListScreen(
        uiState = uiState,
        onPaymentCardClick = onShowPaymentCardDetail,
        onRegisterPaymentCard = onRegisterPaymentCard,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListScreen(
    uiState: CardListUiState,
    onPaymentCardClick: (PaymentCardModel) -> Unit,
    onRegisterPaymentCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Payments")
                },
                actions = {
                    if (uiState is CardListUiState.Many) {
                        TextButton(onClick = onRegisterPaymentCard) {
                            Text(
                                text = "추가",
                                fontWeight = FontWeight.W700,
                                fontSize = 18.sp,
                                color = Color.Black,
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter,
        ) {
            when (uiState) {
                CardListUiState.Empty -> {
                    CardListEmptyScreen(
                        modifier = Modifier.padding(top = 32.dp),
                        onRegisterPaymentCard = onRegisterPaymentCard
                    )
                }

                is CardListUiState.One -> {
                    CardListOneScreen(
                        modifier = Modifier.padding(top = 12.dp),
                        paymentCard = uiState.card,
                        onPaymentCardClick = onPaymentCardClick,
                        onRegisterPaymentCard = onRegisterPaymentCard
                    )
                }

                is CardListUiState.Many -> {
                    CardListManyScreen(
                        modifier = Modifier.padding(top = 12.dp),
                        items = uiState.cards,
                        onPaymentCardClick = onPaymentCardClick,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardListScreenPreviewEmpty() {
    CardListScreen(
        uiState = CardListUiState.Empty,
        onPaymentCardClick = { },
        onRegisterPaymentCard = { },
    )
}

@Preview(showBackground = true)
@Composable
private fun CardListScreenPreviewOne() {
    CardListScreen(
        uiState = CardListUiState.One(
            card = PaymentCardModel(
                cardNumber = "1234-5678-9012-3456",
                expiredDate = "12/25",
                ownerName = "SeokJun Jeong",
                password = "",
            )
        ),
        onPaymentCardClick = { },
        onRegisterPaymentCard = { },
    )
}

@Preview(showBackground = true)
@Composable
private fun CardListScreenPreviewMany() {
    CardListScreen(
        uiState = CardListUiState.Many(
            cards = List(size = 4) {
                PaymentCardModel(
                    cardNumber = "1234-5678-9012-3456",
                    expiredDate = "12/25",
                    ownerName = "SeokJun Jeong",
                    password = "",
                )
            }
        ),
        onPaymentCardClick = { },
        onRegisterPaymentCard = { },
    )
}
