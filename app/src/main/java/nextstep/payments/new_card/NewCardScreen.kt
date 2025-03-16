package nextstep.payments.new_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.component.CardCompanyBottomSheet
import nextstep.payments.component.PaymentCard
import nextstep.payments.component.visiualtransformation.CardNumberTransformation
import nextstep.payments.component.visiualtransformation.DueDateVisualTransformation
import nextstep.payments.model.CardCompany
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardScreen(
    navigateToList: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val sheetState = rememberModalBottomSheetState(confirmValueChange = { false })
    var sheetVisible by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = state.card.company) {
        if (state.card.company != CardCompany.NONE) {
            sheetState.hide()
            sheetVisible = false
        }
    }

    if (sheetVisible) {
        CardCompanyBottomSheet(
            sheetState = sheetState,
            onCompanyClick = viewModel::setCompany
        )
    }

    NewCardScreen(
        modifier = modifier,
        card = state.card,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        onBackClick = navigateToList,
        onSaveClick = {
            viewModel.addCard()
            navigateToList()
        }
    )
}

@Composable
fun NewCardScreen(
    card: CreditCard,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = { NewCardTopBar(onBackClick = onBackClick, onSaveClick = onSaveClick) },
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
            
            PaymentCard(card = CreditCard.emptyCard.copy(company = card.company))
            
            Spacer(modifier = Modifier.height(10.dp))
            
            OutlinedTextField(
                value = card.number,
                onValueChange = setCardNumber,
                label = { Text("카드 번호") },
                placeholder = { Text("0000 - 0000 - 0000 - 0000") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = CardNumberTransformation()
            )
            
            OutlinedTextField(
                value = card.dueDate,
                onValueChange = setExpiredDate,
                label = { Text("만료일") },
                placeholder = { Text("MM / YY") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = DueDateVisualTransformation()
            )
            
            OutlinedTextField(
                value = card.name,
                onValueChange = setOwnerName,
                label = { Text("카드 소유자 이름(선택)") },
                placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
                modifier = Modifier.fillMaxWidth(),
            )
            
            OutlinedTextField(
                value = card.password,
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
                setCardNumber("1234-5678-1234-5678")
                setExpiredDate("12 / 24")
                setOwnerName("홍길동")
                setPassword("1234")
            },
            navigateToList = {}
        )
    }
}

@Preview
@Composable
private fun StatelessNewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            card = CreditCard(
                number = "1234567812345678",
                dueDate = "1224",
                name = "홍길동",
                password = "1234",
                company = CardCompany.NONE
            ),
            setCardNumber = {},
            setExpiredDate = {},
            setOwnerName = {},
            setPassword = {},
            onBackClick = {},
            onSaveClick = {}
        )
    }
}