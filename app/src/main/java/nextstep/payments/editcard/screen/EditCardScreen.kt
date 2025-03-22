package nextstep.payments.editcard.screen

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import nextstep.payments.R
import nextstep.payments.common.model.Card
import nextstep.payments.common.model.CardCompany
import nextstep.payments.common.model.Validation
import nextstep.payments.common.screen.CardFormScreen
import nextstep.payments.editcard.EditCardViewModel
import nextstep.payments.editcard.model.EditCardEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCardScreen(
    cardId: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditCardViewModel = viewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            viewModel.event.collect {
                when (it) {
                    is EditCardEvent.ShowToast -> {
                        Toast.makeText(context, it.resId, Toast.LENGTH_SHORT).show()
                    }

                    is EditCardEvent.Finish -> {
                        Toast.makeText(context, it.noticeResId, Toast.LENGTH_SHORT).show()
                        onBack()
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchCard(cardId)
    }

    EditCardScreen(
        card = uiState.card,
        cardNumberValidation = uiState.cardNumberValidation,
        expiredDateValidation = uiState.expiredDateValidation,
        passwordValidation = uiState.passwordValidation,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        onClickCard = { showBottomSheet = true },
        showBottomSheet = showBottomSheet,
        onDismissRequest = { showBottomSheet = false },
        sheetState = sheetState,
        onClickCardCompany = {
            viewModel.setCardCompany(it)
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) showBottomSheet = false
            }
        },
        onBack = onBack,
        onSave = {
            viewModel.editCard(onComplete = onBack)
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCardScreen(
    card: Card,
    cardNumberValidation: Validation,
    expiredDateValidation: Validation,
    passwordValidation: Validation,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onClickCard: () -> Unit,
    showBottomSheet: Boolean,
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    onClickCardCompany: (CardCompany) -> Unit,
    onBack: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CardFormScreen(
        title = stringResource(R.string.edit_card),
        card = card,
        cardNumberValidation = cardNumberValidation,
        expiredDateValidation = expiredDateValidation,
        passwordValidation = passwordValidation,
        setCardNumber = setCardNumber,
        setExpiredDate = setExpiredDate,
        setOwnerName = setOwnerName,
        setPassword = setPassword,
        onClickCard = onClickCard,
        showBottomSheet = showBottomSheet,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        onClickCardCompany = onClickCardCompany,
        onBack = onBack,
        onSave = onSave,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun EditCardScreenPreview() {
    var card by remember {
        mutableStateOf(
            Card(
                id = 0,
                cardCompany = CardCompany.BC,
                cardNumber = "1234123412341234",
                expiredDate = "0000",
                ownerName = "홍길동",
                password = "0000",
            )
        )
    }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    EditCardScreen(
        card = card,
        cardNumberValidation = Validation.Init,
        expiredDateValidation = Validation.Init,
        passwordValidation = Validation.Init,
        setCardNumber = { card = card.copy(cardNumber = it) },
        setExpiredDate = { card = card.copy(expiredDate = it) },
        setOwnerName = { card = card.copy(ownerName = it) },
        setPassword = { card = card.copy(password = it) },
        onClickCard = { showBottomSheet = true },
        onSave = {},
        onBack = {},
        showBottomSheet = showBottomSheet,
        onDismissRequest = { showBottomSheet = false },
        sheetState = sheetState,
        onClickCardCompany = {
            card = card.copy(cardCompany = it)
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) showBottomSheet = false
            }
        },
    )
}

