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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import nextstep.payments.ui.components.IssuingBankBottomSheet
import nextstep.payments.ui.form.PaymentCardFormScreen

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

    PaymentCardFormScreen(
        formState = uiState,
        snackBarHostState = snackBarHostState,
        onPaymentCardClick = { showBottomSheet = true },
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = viewModel::onSaveClick,
            )
        },
        modifier = modifier,
    )

    if (showBottomSheet) {
        IssuingBankBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            onIssuingBankSelected = { viewModel.setIssuingBank(it) },
        )
    }
}
