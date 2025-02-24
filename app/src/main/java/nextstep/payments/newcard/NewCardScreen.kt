package nextstep.payments.newcard

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.newcard.component.NewCardTopBar
import nextstep.payments.newcard.component.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun NewCardScreen(
    viewModel: NewCardViewModel,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    navigateToCardList: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val cardAdded by viewModel.cardAdded.collectAsStateWithLifecycle()
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()

    LaunchedEffect(cardAdded) {
        if (cardAdded) navigateToCardList()
    }

    NewCardScreen(
        modifier = modifier,
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        onBackClick = { onBackClick() },
        onSaveClick = { onSaveClick() }
    )
}

@Composable
private fun NewCardScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            NewCardTopBar(
                modifier = modifier,
                onBackClick = { onBackClick() },
                onSaveClick = { onSaveClick() },
                title = "카드 추가",
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconContentDescription = "뒤로 가기",
                actionIcon = Icons.Filled.Check,
                actionIconContentDescription = "완료"
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

            PaymentCard()

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = cardNumber,
                onValueChange = setCardNumber,
                label = { Text("카드 번호") },
                placeholder = { Text("0000 - 0000 - 0000 - 0000") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = expiredDate,
                onValueChange = setExpiredDate,
                label = { Text("만료일") },
                placeholder = { Text("MM / YY") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = ownerName,
                onValueChange = setOwnerName,
                label = { Text("카드 소유자 이름(선택)") },
                placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = password,
                onValueChange = setPassword,
                label = { Text("비밀번호") },
                placeholder = { Text("0000") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
            )
        }
    }
}

@Preview
@Composable
private fun StatefulNewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            viewModel = NewCardViewModel().apply {
                setCardNumber("1234 - 5678 - 9012 - 3456")
                setExpiredDate("12 / 25")
                setOwnerName("홍길동")
                setPassword("1234")
            },
            navigateToCardList = {},
            onSaveClick = {},
            onBackClick = {}
        )
    }
}

@Preview
@Composable
private fun StatelessNewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            cardNumber = "1234 - 5678 - 9012 - 3456",
            expiredDate = "12 / 25",
            ownerName = "홍길동",
            password = "1234",
            setCardNumber = {},
            setExpiredDate = {},
            setOwnerName = {},
            setPassword = {},
            onSaveClick = {},
            onBackClick = {}
        )
    }
}
