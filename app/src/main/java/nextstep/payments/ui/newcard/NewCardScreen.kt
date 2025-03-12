package nextstep.payments.ui.newcard

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import nextstep.payments.model.IssuingBank
import nextstep.payments.ui.components.IssuingBankBottomSheet
import nextstep.payments.ui.form.PaymentCardFormScreen
import nextstep.payments.ui.form.PaymentCardFormState
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun NewCardScreen(
    onBackClick: () -> Unit,
    navigateToPayments: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showBottomSheet by remember { mutableStateOf(true) }
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(viewModel) {
        viewModel.effect.collectLatest {
            when (it) {
                is NewCardEffect.ShowError -> {
                    coroutineScope.launch { snackBarHostState.showSnackbar(it.message) }
                }

                is NewCardEffect.CardAdded -> navigateToPayments()
            }
        }
    }

    NewCardScreen(
        formState = uiState,
        snackbarHostState = snackBarHostState,
        onPaymentCardClick = { showBottomSheet = true },
        onBackClick = onBackClick,
        onSaveClick = { viewModel.onSaveClick() },
        modifier = modifier,
    )

    if (showBottomSheet) {
        IssuingBankBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            onIssuingBankSelected = { viewModel.setIssuingBank(it) },
        )
    }
}

@Composable
fun NewCardScreen(
    formState: PaymentCardFormState,
    snackbarHostState: SnackbarHostState,
    onPaymentCardClick: () -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    PaymentCardFormScreen(
        formState = formState,
        snackBarHostState = snackbarHostState,
        onPaymentCardClick = onPaymentCardClick,
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = onSaveClick,
            )
        },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun NewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            formState = PaymentCardFormState(
                cardNumber = "1234 - 5678 - 1234 - 5678",
                expiredDate = "12 / 34",
                ownerName = "홍길동",
                password = "1234",
                issuingBank = IssuingBank.SHINHAN_CARD,
                onCardNumberChanged = {},
                onExpiredDateChanged = {},
                onOwnerNameChanged = {},
                onPasswordChanged = {},
            ),
            snackbarHostState = SnackbarHostState(),
            onPaymentCardClick = {},
            onBackClick = {},
            onSaveClick = {},
        )
    }
}
