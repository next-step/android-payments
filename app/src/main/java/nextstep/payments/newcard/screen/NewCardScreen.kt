package nextstep.payments.newcard.screen

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
import nextstep.payments.newcard.NewCardViewModel
import nextstep.payments.common.model.CardCompany
import nextstep.payments.common.model.Card
import nextstep.payments.common.model.Validation
import nextstep.payments.common.screen.CardFormScreen
import nextstep.payments.newcard.model.NewCardEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardScreen(
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
    onBack: () -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(true) }

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            viewModel.event.collect {
                when (it) {
                    is NewCardEvent.ShowToast -> {
                        Toast.makeText(context, it.resId, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    NewCardScreen(
        card = uiState.card,
        cardNumberValidation = uiState.cardNumberValidation,
        expiredDateValidation = uiState.expiredDateValidation,
        passwordValidation = uiState.passwordValidation,
        onCardNumberChange = viewModel::setCardNumber,
        onExpiredDateChange = viewModel::setExpiredDate,
        onOwnerNameChange = viewModel::setOwnerName,
        onPasswordChange = viewModel::setPassword,
        onCardClick = { showBottomSheet = true },
        showBottomSheet = showBottomSheet,
        onDismissRequest = { showBottomSheet = false },
        sheetState = sheetState,
        onCardCompanyChange = {
            viewModel.setCardCompany(it)
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) showBottomSheet = false
            }
        },
        onBack = onBack,
        onSave = {
            viewModel.addCard(onComplete = onBack)
        },
        modifier = modifier
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardScreen(
    card: Card,
    cardNumberValidation: Validation,
    expiredDateValidation: Validation,
    passwordValidation: Validation,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onCardClick: () -> Unit,
    showBottomSheet: Boolean,
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    onCardCompanyChange: (CardCompany) -> Unit,
    onBack: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CardFormScreen(
        title = stringResource(R.string.add_card),
        card = card,
        cardNumberValidation = cardNumberValidation,
        expiredDateValidation = expiredDateValidation,
        passwordValidation = passwordValidation,
        onCardNumberChange = onCardNumberChange,
        onExpiredDateChange = onExpiredDateChange,
        onOwnerNameChange = onOwnerNameChange,
        onPasswordChange = onPasswordChange,
        onCardClick = onCardClick,
        showBottomSheet = showBottomSheet,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        onCardCompanyChange = onCardCompanyChange,
        onBack = onBack,
        onSave = onSave,
        modifier = modifier
    )
}

@Preview
@Composable
private fun StatefulNewCardScreenPreview() {
    NewCardScreen(
        viewModel = NewCardViewModel().apply {
            setCardNumber("0000000000000000")
            setExpiredDate("0000")
            setOwnerName("홍길동")
            setPassword("0000")
        },
        onBack = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun StatelessNewCardScreenPreview() {
    var card by remember {
        mutableStateOf(
            Card(
                id = 0,
                cardCompany = CardCompany.BC,
                cardNumber = "0000000000000000",
                expiredDate = "0000",
                ownerName = "홍길동",
                password = "0000",
            )
        )
    }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(true) }

    NewCardScreen(
        card = card,
        cardNumberValidation = Validation.Success,
        expiredDateValidation = Validation.Success,
        passwordValidation = Validation.Success,
        onCardNumberChange = {},
        onExpiredDateChange = {},
        onOwnerNameChange = {},
        onPasswordChange = {},
        onCardClick = {},
        onSave = {},
        onBack = {},
        showBottomSheet = showBottomSheet,
        onDismissRequest = { showBottomSheet = false },
        sheetState = sheetState,
        onCardCompanyChange = {
            card = card.copy(cardCompany = it)
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) showBottomSheet = false
            }
        },
    )
}
