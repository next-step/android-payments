package nextstep.payments.ui.cardedit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import nextstep.payments.repository.PaymentCardsRepository
import nextstep.payments.ui.components.IssuingBankBottomSheet
import nextstep.payments.ui.form.PaymentCardFormScreen

@Composable
fun CardEditScreen(
    cardId: Long,
    repository: PaymentCardsRepository,
    onBackClick: () -> Unit,
    navigateToPayments: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val extras = MutableCreationExtras().apply {
        set(CardEditViewModel.CARD_ID_KEY, cardId)
        set(CardEditViewModel.PAYMENTS_CARD_REPOSITORY_KEY, repository)
    }
    val viewModel: CardEditViewModel = viewModel(
        factory = CardEditViewModel.Factory,
        extras = extras,
    )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showBottomSheet by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel) {
        viewModel.effect.collectLatest {
            when (it) {
                is CardEditEffect.ShowError -> snackBarHostState.showSnackbar(it.message)
                is CardEditEffect.OnCardEditSaved -> navigateToPayments()
            }
        }
    }

    when (val state = uiState) {
        is CardEditUiState.Loading -> {
            Box(modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }

        is CardEditUiState.Success -> {
            PaymentCardFormScreen(
                formState = state.formState,
                snackBarHostState = snackBarHostState,
                topBar = {
                    EditCardTopBar(
                        onSaveClick = { viewModel.onIntent(CardEditIntent.OnSaveCardEdit) },
                        onBackClick = onBackClick
                    )
                },
                onPaymentCardClick = { showBottomSheet = true },
                modifier = modifier
            )
        }
    }

    if (showBottomSheet && uiState is CardEditUiState.Success) {
        IssuingBankBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            onIssuingBankSelected = { issuingBank ->
                viewModel.onIntent(CardEditIntent.OnIssuingBankChanged(issuingBank = issuingBank))
            },
        )
    }
}
