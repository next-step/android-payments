package nextstep.payments.screen.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.component.bottomsheet.bank.BankSelectBottomSheet
import nextstep.payments.component.card.PaymentCard
import nextstep.payments.component.textfield.CardNumberTextFiled
import nextstep.payments.component.textfield.ExpiredDateTextFiled
import nextstep.payments.component.textfield.OwnerNameTextFiled
import nextstep.payments.component.textfield.PasswordTextFiled
import nextstep.payments.component.topbar.NewCardTopBar
import nextstep.payments.screen.model.BankTypeUiModel
import nextstep.payments.ui.theme.PaymentsTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NewCardScreen(
    modifier: Modifier = Modifier,
    navigateToCardList: (NewCardEvent) -> Unit,
    viewModel: NewCardViewModel = viewModel(),
) {
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val bankType by viewModel.bankType.collectAsStateWithLifecycle()
    val cardAdded by viewModel.cardAdded.collectAsStateWithLifecycle()
    val modalBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )

    LaunchedEffect(key1 = bankType) {
        if (bankType != null) {
            modalBottomSheetState.hide()
        }
    }

    if(bankType == null){
        BankSelectBottomSheet(
            onBankTypeClick = viewModel::setBankType,
            modalBottomSheetState = modalBottomSheetState
        )
    }

    NewCardScreen(
        modifier = modifier,
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        bankType = bankType,
        cardAdded = cardAdded,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        onBackClick = {
            viewModel.cancelToAddCard()
        },
        onSaveClick = {
            viewModel.addCard()
        },
        navigateToCardList = navigateToCardList
    )
}


@Composable
internal fun NewCardScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    bankType : BankTypeUiModel?,
    cardAdded : NewCardEvent,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    navigateToCardList: (NewCardEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(key1 = cardAdded) {
        if (cardAdded != NewCardEvent.Pending) {
            navigateToCardList(cardAdded)
        }
    }

    Scaffold(
        topBar = {
            NewCardTopBar(
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
                bankType = bankType
            )

            Spacer(modifier = Modifier.height(10.dp))

            CardNumberTextFiled(
                modifier = Modifier.fillMaxWidth(),
                cardNumber = cardNumber,
                onValueChange = setCardNumber
            )

            ExpiredDateTextFiled(
                modifier = Modifier.fillMaxWidth(),
                expiredDate = expiredDate,
                onValueChange = setExpiredDate
            )

            OwnerNameTextFiled(
                modifier = Modifier.fillMaxWidth(),
                ownerName = ownerName,
                onValueChange = setOwnerName
            )

            PasswordTextFiled(
                modifier = Modifier.fillMaxWidth(),
                password = password,
                onValueChange = setPassword
            )
        }
    }
}


@Preview
@Composable
private fun NewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            cardNumber = "0000000000000000",
            expiredDate = "1123",
            ownerName = "김",
            password = "1234",
            cardAdded = NewCardEvent.Pending,
            bankType = BankTypeUiModel.BC,
            setCardNumber = {},
            setExpiredDate = {},
            setOwnerName = {},
            setPassword = {},
            onBackClick = {},
            onSaveClick = {},
            navigateToCardList = {}
        )
    }
}
