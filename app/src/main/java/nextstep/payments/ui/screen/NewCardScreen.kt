package nextstep.payments.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
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

    NewCardScreen(
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        onBackCLick = navigateToCardList,
        onSaveClick = {
            viewModel.addCard(cardNumber, expiredDate, ownerName, password)
            navigateToCardList()
        },
        modifier = modifier
    )
}

@Composable
fun NewCardScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onBackCLick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { NewCardTopBar(onBackClick = onBackCLick, onSaveClick = onSaveClick) },
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
                    if (cardNum.length <= 16) setCardNumber(cardNum)
                },
            )

            ExpireDateInputField(
                expiredDate = expiredDate,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { expiredDate ->
                    if (expiredDate.length <= 4) {
                        setExpiredDate(expiredDate)
                    }
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
                onValueChange = setPassword,
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
        label = "카드 번호",
        placeholder = "0000 - 0000 - 0000 - 0000",
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
        label = "만료일",
        placeholder = "MM / YY",
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
        label = "카드 소유자 이름(선택)",
        placeholder = "카드에 표시된 이름을 입력하세요.",
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
        label = "비밀번호",
        placeholder = "0000",
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
        setCardNumber = {},
        setExpiredDate = {},
        setOwnerName = {},
        setPassword = {},
        onBackCLick = {},
        onSaveClick = {},
    )
}