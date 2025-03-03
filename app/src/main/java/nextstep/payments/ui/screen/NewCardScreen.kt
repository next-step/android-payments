package nextstep.payments.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import nextstep.payments.R
import nextstep.payments.ui.screen.component.NewCardTopBar
import nextstep.payments.ui.screen.component.OutlinedInputTextField
import nextstep.payments.ui.screen.component.PaymentCard
import nextstep.payments.ui.utils.CardNumberVisualTransformation
import nextstep.payments.ui.utils.ExpiryDateVisualTransformation
import nextstep.payments.ui.viewmodel.NewCardViewModel

@Composable
fun NewCardScreen(
    navigateToCardList: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
) {
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()

    // 스낵바 상태 저장
    val snackbarHostState = remember { SnackbarHostState() }

    // 저장 가능 여부 유효성 체크
    val isSaveEnabled = remember(cardNumber, expiredDate, password) {
        cardNumber.length == 16 && expiredDate.length == 4 && password.length == 4
    }

    NewCardScreen(
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        isSaveEnabled = isSaveEnabled,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        snackbarHostState = snackbarHostState,
        onBackCLick = navigateToCardList,
        onSaveClick = {
            viewModel.addCard(cardNumber, expiredDate, ownerName, password)
            navigateToCardList()
        },
        modifier = modifier
    )
}

@Composable
private fun NewCardScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    isSaveEnabled: Boolean,
    snackbarHostState: SnackbarHostState,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onBackCLick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val snackbarMessage = remember { context.getString(R.string.validate_snack_bar_message) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            NewCardTopBar(onBackClick = onBackCLick, onSaveClick = {
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

            PaymentCard(
                cardNumber = cardNumber,
                expiredDate = expiredDate,
                ownerName = ownerName,
            )

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
        cardNumber = "1234567812345678",
        expiredDate = "12 / 34",
        ownerName = "홍길동",
        password = "1234",
        isSaveEnabled = true,
        snackbarHostState = SnackbarHostState(),
        setCardNumber = {},
        setExpiredDate = {},
        setOwnerName = {},
        setPassword = {},
        onBackCLick = {},
        onSaveClick = {},
    )
}
