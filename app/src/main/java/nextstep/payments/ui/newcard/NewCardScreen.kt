package nextstep.payments.ui.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.model.BankType
import nextstep.payments.ui.PaymentCard
import nextstep.payments.ui.component.BankSelectBottomSheet
import nextstep.payments.ui.theme.PaymentsTheme

// Stateful
@Composable
fun NewCardScreen(
    onBackClick: () -> Unit,
    navigateToCardList: () -> Unit,
    viewModel: NewCardViewModel = viewModel(),
) {
    val cardAdded by viewModel.cardAdded.collectAsStateWithLifecycle()

    LaunchedEffect(cardAdded) {
        if (cardAdded) navigateToCardList()
    }

    val canEdit by viewModel.canEdit.collectAsStateWithLifecycle()
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()

    val bankTypes by viewModel.bankTypes.collectAsStateWithLifecycle()
    val selectedBankType by viewModel.selectedBankType.collectAsStateWithLifecycle()

    val isCardModified by viewModel.isCardModified.collectAsStateWithLifecycle()

    var showBankSelectBottomSheet by rememberSaveable { mutableStateOf(!canEdit) }

    NewCardScreen(
        canEdit = canEdit,
        isCardModified = isCardModified,
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        bankTypes = bankTypes,
        selectedBankType = selectedBankType,
        showBankSelectBottomSheet = showBankSelectBottomSheet,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        onBackClick = onBackClick,
        onSaveClick = { if (canEdit) viewModel.modifyCard() else viewModel.addCard() },
        onClickPaymentCard = { showBankSelectBottomSheet = true },
        onClickBankType = { viewModel.setSelectedBankType(it) },
        onBottomSheetDismissRequest = { showBankSelectBottomSheet = false }
    )
}

// Stateless
@Composable
fun NewCardScreen(
    canEdit: Boolean,
    isCardModified: Boolean,
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    bankTypes: List<BankType>,
    selectedBankType: BankType?,
    showBankSelectBottomSheet: Boolean,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    onClickPaymentCard: () -> Unit,
    onClickBankType: (BankType) -> Unit,
    onBottomSheetDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (showBankSelectBottomSheet) {
        BankSelectBottomSheet(
            bankTypes = bankTypes,
            onClickBankType = onClickBankType,
            onDismissRequest = onBottomSheetDismissRequest
        )
    }

    Scaffold(
        topBar = {
            NewCardTopBar(
                canEdit = canEdit,
                isCardModified = isCardModified,
                onBackClick = onBackClick,
                onSaveClick = onSaveClick
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
                selectedBankType = selectedBankType,
                onClickPaymentCard = onClickPaymentCard
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = cardNumber,
                onValueChange = { setCardNumber(it) },
                label = { Text(stringResource(id = R.string.card_number)) },
                placeholder = { Text(stringResource(id = R.string.card_number_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = expiredDate,
                onValueChange = { setExpiredDate(it) },
                label = { Text(stringResource(id = R.string.expire_date)) },
                placeholder = { Text(stringResource(id = R.string.expire_date_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = ownerName,
                onValueChange = { setOwnerName(it) },
                label = { Text(stringResource(id = R.string.card_owner_name)) },
                placeholder = { Text(stringResource(id = R.string.card_owner_name_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = password,
                onValueChange = { setPassword(it) },
                label = { Text(stringResource(id = R.string.password)) },
                placeholder = { Text(stringResource(id = R.string.card_password_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true
            )
        }
    }
}


@Preview
@Composable
private fun StatelessNewCardScreenPrev() {
    PaymentsTheme {
        NewCardScreen(
            canEdit = false,
            isCardModified = false,
            cardNumber = "0000 - 0000 - 0000 - 0000",
            expiredDate = "00 / 00",
            ownerName = "kyudong3",
            password = "asldkfj",
            bankTypes = emptyList(),
            selectedBankType = BankType.BC,
            showBankSelectBottomSheet = false,
            setCardNumber = {},
            setExpiredDate = {},
            setOwnerName = {},
            setPassword = {},
            onBackClick = {},
            onSaveClick = {},
            onClickPaymentCard = {},
            onClickBankType = {},
            onBottomSheetDismissRequest = {}
        )
    }
}

@Preview
@Composable
private fun ModifyCardScreenPrev() {
    PaymentsTheme {
        NewCardScreen(
            canEdit = true,
            isCardModified = false,
            cardNumber = "0000 - 0000 - 0000 - 1234",
            expiredDate = "00 / 00",
            ownerName = "kyudong3",
            password = "asldkfj",
            bankTypes = emptyList(),
            selectedBankType = BankType.WOORI,
            showBankSelectBottomSheet = false,
            setCardNumber = {},
            setExpiredDate = {},
            setOwnerName = {},
            setPassword = {},
            onBackClick = {},
            onSaveClick = {},
            onClickPaymentCard = {},
            onClickBankType = {},
            onBottomSheetDismissRequest = {}
        )
    }
}
