package nextstep.payments.ui.new

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.data.BankType
import nextstep.payments.data.Card
import nextstep.payments.data.bank.BankRepository
import nextstep.payments.ui.component.BankSelectBottomSheet
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.main.PopulatedPaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun NewCardScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    cardCompany: String,
    cardColor: Color,
    bankType: BankType,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    card : Card?,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            NewCardTopBar(
                if (card != null) "카드 수정" else "카드 추가",
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

            if (card != null) {
                PopulatedPaymentCard(
                    card = card
                ) {

                }
            } else {
                PaymentCard(
                    cardCompany = cardCompany,
                    cardColor = cardColor
                )
            }


            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = cardNumber,
                onValueChange = { setCardNumber(it) },
                label = { Text("카드 번호") },
                placeholder = { Text("0000 - 0000 - 0000 - 0000") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = expiredDate,
                onValueChange = { setExpiredDate(it) },
                label = { Text("만료일") },
                placeholder = { Text("MM / YY") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = ownerName,
                onValueChange = { setOwnerName(it) },
                label = { Text("카드 소유자 이름(선택)") },
                placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = password,
                onValueChange = { setPassword(it) },
                label = { Text("비밀번호") },
                placeholder = { Text("0000") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
            )
        }
    }
}

@Composable
fun NewCardScreen(
    viewModel: NewCardViewModel,
    bankRepository: BankRepository,
    navigateToCardList: () -> Unit,
    card : Card?,
    modifier: Modifier = Modifier
) {
    val cardAdded by viewModel.cardAdded.collectAsStateWithLifecycle()

    LaunchedEffect(cardAdded) {
        if (cardAdded) navigateToCardList()
    }
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val cardCompany by viewModel.cardCompany.collectAsStateWithLifecycle()
    val cardColor by viewModel.cardColor.collectAsStateWithLifecycle()
    val bankType by viewModel.bankType.collectAsStateWithLifecycle()

    if (bankType == BankType.NOT_SELECTED) {
        BankSelectBottomSheet(
            banks = bankRepository.getBanks(),
            onDismiss = { bank ->
                viewModel.setBankType(bank.bankType)
                viewModel.setCardCompany(bank.name)
                viewModel.setCardColor(bank.color)
            }
        )
    }

    NewCardScreen(
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        cardCompany = cardCompany,
        cardColor = cardColor,
        bankType = bankType,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        onBackClick = { navigateToCardList() },
        onSaveClick = {
            if (card != null) {
                viewModel.modifyCard(card.copy(
                    cardNumber = cardNumber,
                    expiredDate = expiredDate,
                    ownerName = ownerName,
                    password = password,
                    cardCompany = cardCompany,
                    cardColor = cardColor.toArgb(),
                    bankType = bankType
                ))
            } else {
                viewModel.addCard(
                    Card(
                        cardNumber = cardNumber,
                        expiredDate = expiredDate,
                        ownerName = ownerName,
                        password = password,
                        cardCompany = cardCompany,
                        cardColor = cardColor.toArgb(),
                        bankType = bankType
                    )
                )
            }

        },
        card = card,
        modifier = modifier
    )
}

@Preview
@Composable
private fun StatelessNewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            cardNumber = "0000 - 0000 - 0000 - 0000",
            expiredDate = "00 / 00",
            ownerName = "윤성현",
            password = "1234",
            cardCompany = "롯데카드",
            cardColor = Color.White,
            bankType = BankType.LOTTE,
            setCardNumber = {},
            setExpiredDate = {},
            setOwnerName = {},
            setPassword = {},
            onBackClick = {},
            onSaveClick = {},
            card = Card(cardNumber = "0000 - 0000 - 0000 - 0000",
                expiredDate = "00 / 00",
                ownerName = "윤성현",
                password = "1234",
                cardCompany = "롯데카드",
                cardColor = Color.White.toArgb(),
                bankType = BankType.LOTTE
            )
        )
    }
}
