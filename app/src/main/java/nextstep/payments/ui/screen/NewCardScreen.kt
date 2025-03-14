package nextstep.payments.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import nextstep.payments.R
import nextstep.payments.ui.CardCompanyType
import nextstep.payments.ui.screen.component.NewCardTopBar
import nextstep.payments.ui.screen.component.OutlinedInputTextField
import nextstep.payments.ui.screen.component.PaymentCard
import nextstep.payments.ui.theme.Dimensions
import nextstep.payments.ui.utils.CardNumberVisualTransformation
import nextstep.payments.ui.utils.ExpiryDateVisualTransformation
import nextstep.payments.ui.viewmodel.NewCardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardScreen(
    cardId: String?,
    navigateToCardList: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
) {
    val context = LocalContext.current

    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val isSaveEnabled by viewModel.isSaveEnabled.collectAsStateWithLifecycle()

    val selectedCardCompany by viewModel.selectedCardCompany.collectAsStateWithLifecycle()
    var isBottomSheetVisible by remember { mutableStateOf(true) }
    var sheetState = rememberModalBottomSheetState(
        confirmValueChange = { newState ->
            newState != SheetValue.Hidden
        }
    )

    // 스낵바 상태 저장
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage = remember { context.getString(R.string.validate_modify_snack_bar_message) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        if (cardId != null) {
            viewModel.fetchCardById(cardId)
            isBottomSheetVisible = false
        }
    }

    LaunchedEffect(isBottomSheetVisible) {
        if (isBottomSheetVisible) {
            sheetState.show()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.updateCardState.collect { isUpdatable ->
            if (!isUpdatable) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(snackbarMessage)
                }

                return@collect
            }

            navigateToCardList()
        }
    }

    NewCardScreen(
        cardId = cardId,
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        selectedCardCompany = selectedCardCompany,
        isSaveEnabled = isSaveEnabled,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        snackbarHostState = snackbarHostState,
        onBackCLick = navigateToCardList,
        onSaveClick = {
            viewModel.addCard(
                cardNumber = cardNumber,
                expiredDate = expiredDate,
                ownerName = ownerName,
                password = password,
                cardCompanyType = selectedCardCompany
            )

            navigateToCardList()
        },
        onUpdateClick = {
            if (cardId == null) {
                return@NewCardScreen
            }

            viewModel.updateCard(
                cardId = cardId,
                cardNumber = cardNumber,
                expiredDate = expiredDate,
                ownerName = ownerName,
                password = password,
                cardCompanyType = selectedCardCompany
            )
        },
        modifier = modifier
    )

    if (isBottomSheetVisible) {
        BankBottomModalSheet(
            sheetState = sheetState,
            onBankClick = { selectedCardCompanyType ->
                viewModel.setSelectedBank(selectedCardCompanyType)
                coroutineScope.launch {
                    sheetState.hide()
                    isBottomSheetVisible = false
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun NewCardScreen(
    cardId: String?,
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    selectedCardCompany: CardCompanyType?,
    isSaveEnabled: Boolean,
    snackbarHostState: SnackbarHostState,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onBackCLick: () -> Unit,
    onSaveClick: () -> Unit,
    onUpdateClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val snackbarMessage = remember { context.getString(R.string.validate_snack_bar_message) }

    val coroutineScope = rememberCoroutineScope()

    val appBarTitle = if (cardId == null) {
        stringResource(R.string.card_add_app_bar_title)
    } else {
        stringResource(R.string.card_update_app_bar_title)
    }

    Scaffold(
        topBar = {
            NewCardTopBar(
                appbarTitle = appBarTitle,
                onBackClick = onBackCLick,
                onSaveClick = {
                    if (cardId != null && isSaveEnabled) {
                        onUpdateClick()
                        return@NewCardTopBar
                    }

                    if (isSaveEnabled) {
                        onSaveClick()
                        return@NewCardTopBar
                    }

                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(snackbarMessage)
                    }
                })
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.semantics { contentDescription = "validateSnackbar" }
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

            if (selectedCardCompany != null) {
                PaymentCard(
                    bankName = stringResource(selectedCardCompany.bankNameResId),
                    cardNumber = cardNumber,
                    expiredDate = expiredDate,
                    ownerName = ownerName,
                    cardColor = selectedCardCompany.bankThemeColor,
                )
            } else {
                PaymentCard(
                    bankName = "00",
                    cardNumber = cardNumber,
                    expiredDate = expiredDate,
                    ownerName = ownerName,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            CardNumberInputField(
                cardNumber = cardNumber,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { cardNum ->
                    setCardNumber(cardNum.take(16))
                },
            )

            ExpireDateInputField(
                expiredDate = expiredDate,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { expiredDate ->
                    setExpiredDate(expiredDate.take(4))
                },
            )

            OwnerNameInputField(
                ownerName = ownerName,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = setOwnerName,
            )

            PasswordInputField(
                password = password,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { password ->
                    setPassword(password.take(4))
                },
            )

        }
    }
}

@Composable
private fun CardNumberInputField(
    cardNumber: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedInputTextField(
        value = cardNumber,
        label = stringResource(R.string.card_number_input_label),
        placeholder = stringResource(R.string.card_number_input_placeholder),
        modifier = modifier,
        keyboardType = KeyboardType.Number,
        visualTransformation = CardNumberVisualTransformation(),
        onValueChange = onValueChange,
    )
}

@Composable
private fun ExpireDateInputField(
    expiredDate: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedInputTextField(
        value = expiredDate,
        label = stringResource(R.string.expire_date_input_label),
        placeholder = stringResource(R.string.expire_date_input_placeholder),
        keyboardType = KeyboardType.Number,
        visualTransformation = ExpiryDateVisualTransformation(),
        onValueChange = onValueChange,
        modifier = modifier,
    )
}

@Composable
private fun OwnerNameInputField(
    ownerName: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedInputTextField(
        value = ownerName,
        label = stringResource(R.string.owner_name_input_label),
        placeholder = stringResource(R.string.owner_name_input_placeholder),
        keyboardType = KeyboardType.Text,
        onValueChange = onValueChange,
        modifier = modifier,
    )
}

@Composable
private fun PasswordInputField(
    password: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedInputTextField(
        value = password,
        label = stringResource(R.string.password_input_label),
        placeholder = stringResource(R.string.password_input_placeholder),
        visualTransformation = PasswordVisualTransformation(),
        onValueChange = onValueChange,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BankBottomModalSheet(
    sheetState: SheetState,
    onBankClick: (CardCompanyType) -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        modifier = modifier.semantics {
            contentDescription = "bankBottomSheet"
        },
        sheetState = sheetState,
        onDismissRequest = {},
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            BackHandler(sheetState.isVisible) {}

            // 은행 리스트
            BankSelectRow(
                onBankClick = { bankType ->
                    onBankClick(bankType)
                },
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BankSelectRow(
    onBankClick: (CardCompanyType) -> Unit,
    modifier: Modifier = Modifier,
) {
    val bankList = CardCompanyType.getBankList()

    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 35.dp, horizontal = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = 4
    ) {
        bankList.forEach { bankType ->

            if (bankType.bankImageRes == null) {
                return@forEach
            }

            BankItem(
                bankName = stringResource(bankType.bankNameResId),
                bankImage = painterResource(bankType.bankImageRes),
                modifier = modifier
                    .width(80.dp)
                    .clickable(
                        onClick = { onBankClick(bankType) },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
            )
        }
    }
}

@Composable
private fun BankItem(
    bankName: String,
    bankImage: Painter,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = bankImage,
            contentDescription = "Bank Logo",
            modifier = Modifier.size(Dimensions.LogoDefaults),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "${bankName}카드",
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BankItemPreview() {
    BankItem(
        bankName = "BC",
        bankImage = painterResource(R.drawable.bc),
    )
}


@Preview(showBackground = true)
@Composable
private fun BankSelectRowPreview() {
    BankSelectRow(
        onBankClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun CardNumberInputFieldPreview() {
    CardNumberInputField(
        cardNumber = "1234567812345678",
        onValueChange = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun ExpireDateInputFieldPreview() {
    ExpireDateInputField(
        expiredDate = "1230",
        onValueChange = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun OwnerNameInputFieldPreview() {
    OwnerNameInputField(
        ownerName = "홍길동",
        onValueChange = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordInputFieldPreview() {
    PasswordInputField(
        password = "1234",
        onValueChange = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun StatefulNewCardScreenPreview() {
    NewCardScreen(
        cardId = null,
        viewModel = NewCardViewModel().apply {
            setCardNumber("1234567812345678")
            setExpiredDate("12 / 34")
            setOwnerName("홍길동")
            setPassword("1234")
        },
        navigateToCardList = {},
    )
}

@Preview
@Composable
private fun StatelessNewCardScreenPreView() {
    NewCardScreen(
        cardId = null,
        cardNumber = "1234567812345678",
        expiredDate = "12 / 34",
        ownerName = "홍길동",
        password = "1234",
        isSaveEnabled = true,
        selectedCardCompany = CardCompanyType.BC,
        snackbarHostState = SnackbarHostState(),
        setCardNumber = {},
        setExpiredDate = {},
        setOwnerName = {},
        setPassword = {},
        onBackCLick = {},
        onSaveClick = {},
        onUpdateClick = {},
    )
}
