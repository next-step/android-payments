package nextstep.payments.ui.payments

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.ui.payments.component.PaymentCardList

@Composable
fun PaymentCardsScreenRoute(
    onAddCardClick: () -> Unit,
    viewModel: PaymentCardsViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    PaymentCardsScreen(
        uiState = uiState,
        onAddCardClick = onAddCardClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PaymentCardsScreen(
    uiState: PaymentCardsUiState,
    onAddCardClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.payments_title),
                        fontSize = 22.sp
                    )
                },
                actions = {
                    if (uiState is PaymentCardsUiState.Many) {
                        TextButton(onClick = { onAddCardClick() }) {
                            Text(
                                text = stringResource(id = R.string.payments_add_card),
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                },
            )
        }
    ) { innerPadding ->
        PaymentCardList(
            modifier = Modifier.padding(innerPadding),
            onAddCardClick = onAddCardClick,
            uiState = uiState
        )
    }
}

@Preview
@Composable
fun PaymentCardsScreenPreview(
    @PreviewParameter(PaymentCardsUiStatePreviewParameterProvider::class) paymentCardsUiState: PaymentCardsUiState
) {
    PaymentCardsScreen(
        uiState = paymentCardsUiState,
        onAddCardClick = {}
    )
}
