package nextstep.payments.ui.payments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.components.PaymentCard
import nextstep.payments.ui.components.PaymentCardAddition
import nextstep.payments.ui.theme.PaymentsTheme


@Composable
fun PaymentsScreen(
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PaymentsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (val currentState = uiState) {
        is PaymentsUiState.Empty -> PaymentsEmptyScreen(onAddCardClick, modifier)
        is PaymentsUiState.One -> PaymentsOneScreen(currentState, onAddCardClick, modifier)
        is PaymentsUiState.Many -> PaymentsManyScreen(currentState, onAddCardClick, modifier)
    }
}

@Composable
private fun PaymentsEmptyScreen(
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier, topBar = {
        PaymentsTopBar(isAddable = false)
    }) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                stringResource(R.string.payments_empty_headline),
                fontWeight = W700,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            PaymentCardAddition(onClick = onAddCardClick)
        }
    }
}

@Composable
private fun PaymentsOneScreen(
    uiState: PaymentsUiState.One,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = { PaymentsTopBar(isAddable = false) }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            PaymentCard(uiState.card)
            Spacer(modifier = Modifier.height(32.dp))
            PaymentCardAddition(onClick = onAddCardClick)
        }
    }
}

@Composable
private fun PaymentsManyScreen(
    uiState: PaymentsUiState.Many,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = { PaymentsTopBar(isAddable = true, onAddClick = onAddCardClick) }
    ) { innerPadding ->
        LazyColumn(
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(36.dp),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
        ) {
            items(uiState.cards) { card ->
                PaymentCard(card)
            }
        }
    }
}

@Preview
@Composable
private fun PaymentsEmptyScreenPreview() {
    PaymentsTheme {
        PaymentsEmptyScreen(onAddCardClick = {})
    }
}

@Preview
@Composable
private fun PaymentsOneScreenPreview() {
    PaymentsTheme {
        PaymentsOneScreen(
            uiState = PaymentsUiState.One(
                CreditCard(
                    cardNumber = "1234567812345678",
                    expiredDate = "0101",
                    ownerName = "홍길동",
                    password = "123"
                )
            ),
            onAddCardClick = {}
        )
    }
}

@Preview
@Composable
private fun PaymentsManyScreenPreview() {
    PaymentsTheme {
        PaymentsManyScreen(
            uiState = PaymentsUiState.Many(
                listOf(
                    CreditCard(
                        cardNumber = "1234567812345678",
                        expiredDate = "1231",
                        ownerName = "홍길동",
                        password = "123"
                    ),
                    CreditCard(
                        cardNumber = "1234567812345678",
                        expiredDate = "1231",
                        ownerName = "홍길동",
                        password = "123"
                    ),
                )
            ),
            onAddCardClick = {}
        )
    }
}