package nextstep.payments.ui.card.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.data.BankType
import nextstep.payments.ui.PaymentCard
import nextstep.payments.ui.card.registration.component.BankSelectRow
import nextstep.payments.ui.card.registration.component.NewCardTopBar
import nextstep.payments.ui.theme.PaymentsTheme
import kotlin.reflect.KFunction0

// Stateful
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardScreen(
    modifier: Modifier = Modifier,
    navigateToCardList: () -> Unit,
    onBackClick: () -> Unit = {},
    viewModel: NewCardViewModel = viewModel(),
) {
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val cardAdded by viewModel.cardAdded.collectAsStateWithLifecycle()
    val selectedBankType by viewModel.selectedBankType.collectAsStateWithLifecycle()
    var showCardCompanyBottomSheet by rememberSaveable { mutableStateOf(true) }
    val modalBottomSheetState = rememberModalBottomSheetState(confirmValueChange = { false })
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(cardAdded) {
        if (cardAdded) navigateToCardList()
    }

    LaunchedEffect(key1 = selectedBankType) {
        if (selectedBankType != BankType.NOT_SELECTED) {
            modalBottomSheetState.hide()
        }
    }

    LaunchedEffect(key1 = uiState) {
        if (uiState == RegistrationUiState.EditCard) {
            showCardCompanyBottomSheet = false
        }
    }

    if (showCardCompanyBottomSheet) {
        ModalBottomSheet(
            sheetState = modalBottomSheetState,
            onDismissRequest = { }
        ) {
            BankSelectRow(
                onClickBankType = {
                    showCardCompanyBottomSheet = false
                    viewModel.setSelectBankType(it)
                }
            )
        }
    }
    val saveFunction: KFunction0<Unit> = when (uiState) {
        RegistrationUiState.NewCard -> {
            viewModel::addCard
        }

        RegistrationUiState.EditCard -> {
            viewModel::editCard
        }
    }

    NewCardScreen(
        modifier = modifier,
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        bankType = selectedBankType,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDatedNumber = viewModel::setExpiredDate,
        setOwnerNamedNumber = viewModel::setOwnerName,
        setPasswordNumber = viewModel::setPassword,
        onBackClick = onBackClick,
        onSaveClick = saveFunction,
    )
}

// Stateless
@Composable
private fun NewCardScreen(
    modifier: Modifier = Modifier,
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    bankType: BankType,
    setCardNumber: (String) -> Unit,
    setExpiredDatedNumber: (String) -> Unit,
    setOwnerNamedNumber: (String) -> Unit,
    setPasswordNumber: (String) -> Unit,
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            NewCardTopBar(onBackClick = onBackClick, onSaveClick = {
                onSaveClick()
            })
        }, modifier = modifier
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
                brandColor = colorResource(bankType.brandColor)
            )


            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = cardNumber,
                onValueChange = { setCardNumber(it) },
                label = { Text(stringResource(id = R.string.text_card_number)) },
                placeholder = { Text(stringResource(id = R.string.placeholder_card_number)) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = expiredDate,
                onValueChange = { setExpiredDatedNumber(it) },
                label = { Text(stringResource(id = R.string.text_card_expiration_date)) },
                placeholder = { Text(stringResource(id = R.string.placeholder_card_expiration_date)) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = ownerName,
                onValueChange = { setOwnerNamedNumber(it) },
                label = { Text(stringResource(id = R.string.text_card_owner_name)) },
                placeholder = { Text(stringResource(id = R.string.placeholder_card_owner_name)) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = password,
                onValueChange = { setPasswordNumber(it) },
                label = { Text(stringResource(id = R.string.text_password)) },
                placeholder = { Text(stringResource(id = R.string.placeholder_password)) },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
            )
        }
    }
}

@Preview
@Composable
private fun NewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(modifier = Modifier,
            navigateToCardList = {},
            viewModel = NewCardViewModel().apply {
                setCardNumber("0000 - 0000 - 0000 -0000")
                setExpiredDate("02/26")
                setOwnerName("김수현")
                setPassword("1234")
            })
    }
}

@Preview
@Composable
private fun StatelessNewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(cardNumber = "0000 - 0000 - 0000 -0000",
            expiredDate = "02/26",
            ownerName = "김수현",
            password = "1234",
            bankType = BankType.NOT_SELECTED,
            setCardNumber = {},
            setExpiredDatedNumber = {},
            setOwnerNamedNumber = {},
            setPasswordNumber = {})
    }
}
