package nextstep.payments.newcard.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import nextstep.payments.newcard.NewCardViewModel
import nextstep.payments.newcard.component.NewCardTopBar
import nextstep.payments.common.component.PaymentCard
import nextstep.payments.common.model.CardCompany
import nextstep.payments.common.model.Card
import nextstep.payments.newcard.component.CardCompanySelectBottomSheet
import nextstep.payments.newcard.component.CardNumberTextField
import nextstep.payments.newcard.component.ExpiredDateTextField
import nextstep.payments.newcard.component.OwnerNameTextField
import nextstep.payments.newcard.component.PasswordTextField
import nextstep.payments.newcard.model.NewCardEvent
import nextstep.payments.newcard.model.Validation

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
    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = onBack,
                onSaveClick = onSave
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            PaymentCard(
                modifier = Modifier.clickable { onClickCard() },
                cardCompany = card.cardCompany
            )

            Spacer(modifier = Modifier.height(10.dp))

            CardNumberTextField(
                modifier = Modifier.fillMaxWidth(),
                cardNumber = card.cardNumber,
                validation = cardNumberValidation,
                setCardNumber = setCardNumber
            )

            ExpiredDateTextField(
                modifier = Modifier.fillMaxWidth(),
                expiredDate = card.expiredDate,
                validation = expiredDateValidation,
                setExpiredDate = setExpiredDate
            )

            OwnerNameTextField(
                modifier = Modifier.fillMaxWidth(),
                ownerName = card.ownerName,
                setOwnerName = setOwnerName
            )

            PasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                password = card.password,
                validation = passwordValidation,
                setPassword = setPassword
            )
        }

        if (showBottomSheet) {
            CardCompanySelectBottomSheet(
                sheetState = sheetState,
                onClickCardCompany = onClickCardCompany,
                onDismissRequest = onDismissRequest,
            )
        }
    }
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
        setCardNumber = {},
        setExpiredDate = {},
        setOwnerName = {},
        setPassword = {},
        onClickCard = {},
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
