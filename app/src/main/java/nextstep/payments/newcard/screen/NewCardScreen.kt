package nextstep.payments.newcard.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import nextstep.payments.newcard.NewCardViewModel
import nextstep.payments.newcard.component.NewCardTopBar
import nextstep.payments.common.component.PaymentCard
import nextstep.payments.common.model.Bank
import nextstep.payments.common.model.Card
import nextstep.payments.newcard.component.BankSelectBottomSheetContent
import nextstep.payments.newcard.component.CardNumberTextField
import nextstep.payments.newcard.component.ExpiredDateTextField
import nextstep.payments.newcard.component.OwnerNameTextField
import nextstep.payments.newcard.component.PasswordTextField
import nextstep.payments.newcard.model.Validation

@Composable
fun NewCardScreen(
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NewCardScreen(
        card = uiState.card,
        cardNumberValidation = uiState.cardNumberValidation,
        expiredDateValidation = uiState.expiredDateValidation,
        passwordValidation = uiState.passwordValidation,
        setBank = viewModel::setBank,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        onBack = onBack,
        onSave = {
            if (uiState.cardNumberValidation is Validation.Success
                && uiState.expiredDateValidation is Validation.Success
                && uiState.passwordValidation is Validation.Success
            ) {
                viewModel.addCard()
                onBack()
            }
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
    setBank: (Bank) -> Unit,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onBack: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(true) }

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

            PaymentCard(bank = card.bank)

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
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                BankSelectBottomSheetContent(
                    banks = Bank.entries,
                    onClickBank = {
                        setBank(it)
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) showBottomSheet = false
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}


@Preview
@Composable
private fun StatefulNewCardScreenPreview() {
    NewCardScreen(
        viewModel = NewCardViewModel().apply {
            setCardNumber("0000 - 0000 - 0000 - 0000")
            setExpiredDate("00 / 00")
            setOwnerName("홍길동")
            setPassword("0000")
        },
        onBack = {}
    )
}

@Preview
@Composable
private fun StatelessNewCardScreenPreview() {
    NewCardScreen(
        card = Card(
            bank = Bank.BC,
            cardNumber = "0000 - 0000 - 0000 - 0000",
            expiredDate = "00 / 00",
            ownerName = "홍길동",
            password = "0000",
        ),
        cardNumberValidation = Validation.Success,
        expiredDateValidation = Validation.Success,
        passwordValidation = Validation.Success,
        setBank = {},
        setCardNumber = {},
        setExpiredDate = {},
        setOwnerName = {},
        setPassword = {},
        onSave = {},
        onBack = {}
    )
}
