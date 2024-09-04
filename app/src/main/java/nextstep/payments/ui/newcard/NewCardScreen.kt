package nextstep.payments.ui.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nextstep.payments.R
import nextstep.payments.ui.component.card.CardBankInformation
import nextstep.payments.ui.component.card.PaymentCard
import nextstep.payments.ui.component.text.input.CardNumberVisualTransformation
import nextstep.payments.ui.component.text.input.ExpirationDateVisualTransformation
import nextstep.payments.ui.newcard.component.BankBottomSheetContent
import nextstep.payments.ui.newcard.component.NewCardTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NewCardScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    bank: CardBankInformation,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onAddCardClick: () -> Unit,
    onBackClick: () -> Unit,
    onBankClick: (CardBankInformation) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded,
            confirmValueChange = { false },
            skipHiddenState = false
        )
    )
    val coroutineScope = rememberCoroutineScope()

    // 카드 추가 화면
    BottomSheetScaffold(
        modifier = modifier,
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = onAddCardClick
            )
        },
        content = { innerPadding ->
            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(14.dp))

                PaymentCard(cardBankInformation = bank)

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = cardNumber,
                    onValueChange = { newValue ->
                        // 숫자가 아닌 문자를 필터링하여 새로운 값으로 설정
                        val filteredValue = newValue.filter { it.isDigit() }
                        // 16자 제한
                        if (filteredValue.length <= 16) {
                            setCardNumber(filteredValue)

                            if (filteredValue.length == 16) {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        }
                    },
                    label = { Text(text = stringResource(id = R.string.card_number)) },
                    placeholder = { Text(text = stringResource(id = R.string.card_number_placeholder)) },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = CardNumberVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    )
                )

                OutlinedTextField(
                    value = expiredDate,
                    onValueChange = { newValue ->
                        // 숫자가 아닌 문자를 필터링하여 새로운 값으로 설정
                        val filteredValue = newValue.filter { it.isDigit() }
                        // 4자 제한
                        if (filteredValue.length <= 4) {
                            setExpiredDate(filteredValue)

                            if (filteredValue.length == 4) {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        }
                    },
                    label = { Text(text = stringResource(id = R.string.expiry_date)) },
                    placeholder = { Text(text = stringResource(id = R.string.expiry_date_placeholder)) },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = ExpirationDateVisualTransformation(),
                )

                OutlinedTextField(
                    value = ownerName,
                    onValueChange = setOwnerName,
                    label = { Text(text = stringResource(id = R.string.card_owner_name)) },
                    placeholder = { Text(text = stringResource(id = R.string.card_owner_name_placeholder)) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                    )
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { newValue ->
                        // 숫자가 아닌 문자를 필터링하여 새로운 값으로 설정
                        val filteredValue = newValue.filter { it.isDigit() }
                        // 4자 제한
                        if (filteredValue.length <= 4) {
                            setPassword(filteredValue)
                        }
                    },
                    label = { Text(text = stringResource(R.string.password)) },
                    placeholder = { Text(text = stringResource(R.string.password_placeholder)) },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    )
                )
            }
        },
        sheetContent = {
            // 카드사 선택 바텀 시트
            BankBottomSheetContent(
                onBankClick = {
                    onBankClick(it)
                    coroutineScope.launch { scaffoldState.bottomSheetState.hide() }
                },
            )
        },
        scaffoldState = scaffoldState,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewNewCardScreen() {
    val cardNumber = remember { mutableStateOf("1111222233334444") }
    val expiredDate = remember { mutableStateOf("1234") }
    val ownerName = remember { mutableStateOf("홍길동") }
    val password = remember { mutableStateOf("1234") }

    NewCardScreen(
        cardNumber = cardNumber.value,
        expiredDate = expiredDate.value,
        ownerName = ownerName.value,
        password = password.value,
        bank = CardBankInformation.None,
        setCardNumber = { cardNumber.value = it },
        setExpiredDate = { expiredDate.value = it },
        setOwnerName = { ownerName.value = it },
        setPassword = { password.value = it },
        onAddCardClick = {},
        onBackClick = {},
        onBankClick = {},
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewEmptyNewCardScreen() {
    val cardNumber = remember { mutableStateOf("") }
    val expiredDate = remember { mutableStateOf("") }
    val ownerName = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    NewCardScreen(
        cardNumber = cardNumber.value,
        expiredDate = expiredDate.value,
        ownerName = ownerName.value,
        password = password.value,
        bank = CardBankInformation.None,
        setCardNumber = { cardNumber.value = it },
        setExpiredDate = { expiredDate.value = it },
        setOwnerName = { ownerName.value = it },
        setPassword = { password.value = it },
        onAddCardClick = {},
        onBackClick = {},
        onBankClick = {},
        modifier = Modifier
    )
}