package nextstep.payments.screen.cardmanage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import nextstep.payments.component.bottomsheet.bank.BankSelectBottomSheet
import nextstep.payments.component.card.PaymentCard
import nextstep.payments.component.textfield.CardNumberTextFiled
import nextstep.payments.component.textfield.ExpiredDateTextFiled
import nextstep.payments.component.textfield.OwnerNameTextFiled
import nextstep.payments.component.textfield.PasswordTextFiled
import nextstep.payments.component.topbar.ManageCardTopBar
import nextstep.payments.screen.model.BankTypeUiModel
import nextstep.payments.screen.model.ManageCardType
import nextstep.payments.screen.model.toManageCardType
import nextstep.payments.ui.theme.PaymentsTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ManageCardRouteScreen(
    modifier: Modifier = Modifier,
    navigateToCardList: (ManageCardEvent) -> Unit,
    viewModel: ManageCardViewModel = viewModel(),
) {
    val manageCardType by viewModel.cardArgType
        .map { it.toManageCardType() }.collectAsStateWithLifecycle(ManageCardType.ADD)
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val bankType by viewModel.bankType.collectAsStateWithLifecycle()
    val cardChanged by viewModel.cardChanged.collectAsStateWithLifecycle()
    val isSaveCardEnabled by viewModel.isSaveCardEnabled.collectAsStateWithLifecycle()
    val modalBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = {
            bankType != null
        }
    )
    var shouldHideBottomSheet by remember { mutableStateOf(false) }
    var isShownBottomSheet by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = cardChanged) {
        if (cardChanged != ManageCardEvent.Pending) {
            navigateToCardList(cardChanged)
        }
    }

    LaunchedEffect(modalBottomSheetState.targetValue) {
        if (modalBottomSheetState.hasExpandedState && modalBottomSheetState.targetValue == SheetValue.Hidden) {
            if( bankType == null) {
                viewModel.cancelToChangeCard()
            }
            else {
                shouldHideBottomSheet = true
            }
        }
    }

    LaunchedEffect(key1 = cardChanged) {
        if (cardChanged != ManageCardEvent.Pending) {
            navigateToCardList(cardChanged)
        }
    }

    LaunchedEffect(key1 = bankType) {
        if (bankType != null) {
            shouldHideBottomSheet = true
        }
    }

    LaunchedEffect(shouldHideBottomSheet) {
        if(shouldHideBottomSheet){
            launch {
                modalBottomSheetState.hide()
            }.invokeOnCompletion {
                isShownBottomSheet = false
                shouldHideBottomSheet = false
            }
        }
    }

    if (isShownBottomSheet) {
        BankSelectBottomSheet(
            onBankTypeClick = viewModel::setBankType,
            modalBottomSheetState = modalBottomSheetState,
            onDismissRequest = {
                shouldHideBottomSheet = true
            },
            modifier = Modifier.testTag("BankSelectBottomSheet")
        )
    }

    ManageCardScreen(
        modifier = modifier,
        manageCardType = manageCardType,
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        bankType = bankType,
        isSaveCardEnabled = isSaveCardEnabled,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        onCardClick = {
            isShownBottomSheet = true
        },
        onBackClick = {
            viewModel.cancelToChangeCard()
        },
        onSaveClick = {
            viewModel.saveCard()
        }
    )
}


@Composable
internal fun ManageCardScreen(
    manageCardType : ManageCardType,
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    bankType: BankTypeUiModel?,
    isSaveCardEnabled : Boolean,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onCardClick : () -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            ManageCardTopBar(
                manageCardType = manageCardType,
                isSaveCardEnabled = isSaveCardEnabled,
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
                bankType = bankType,
                onClick = onCardClick
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


@Preview(showBackground = true, name = "AddCardScreenPreview")
@Composable
private fun Preview1() {
    PaymentsTheme {
        ManageCardScreen(
            manageCardType = ManageCardType.ADD,
            cardNumber = "0000000000000000",
            expiredDate = "1123",
            ownerName = "김",
            password = "1234",
            bankType = BankTypeUiModel.BC,
            isSaveCardEnabled = true,
            setCardNumber = {},
            setExpiredDate = {},
            setOwnerName = {},
            setPassword = {},
            onCardClick = {},
            onBackClick = {},
            onSaveClick = {}
        )
    }
}


@Preview(showBackground = true, name = "EditCardScreenPreview")
@Composable
private fun Preview2() {
    PaymentsTheme {
        ManageCardScreen(
            manageCardType = ManageCardType.EDIT,
            cardNumber = "0000000000000000",
            expiredDate = "1123",
            ownerName = "김",
            password = "1234",
            bankType = BankTypeUiModel.BC,
            isSaveCardEnabled = true,
            setCardNumber = {},
            setExpiredDate = {},
            setOwnerName = {},
            setPassword = {},
            onCardClick = {},
            onBackClick = {},
            onSaveClick = {}
        )
    }
}
