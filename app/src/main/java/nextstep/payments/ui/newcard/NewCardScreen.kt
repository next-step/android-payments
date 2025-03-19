package nextstep.payments.ui.newcard

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.ui.newcard.component.NewCardTopBar
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.model.CreditCardType
import nextstep.payments.ui.newcard.component.SelectCardBottomSheet
import nextstep.payments.ui.newcard.model.CardCompany
import nextstep.payments.ui.newcard.model.NewCardUiState
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun NewCardScreen(
    navigateToCardList: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.inputInValidMessage) {
        if (uiState.inputInValidMessage.isNotEmpty()) {
            snackbarHostState.showSnackbar(uiState.inputInValidMessage)
        }
    }
    LaunchedEffect(uiState.cardAdded) {
        if (uiState.cardAdded) navigateToCardList()
    }
    LaunchedEffect(Unit) {
        viewModel.showBottomSheet()
    }
    if (uiState.showSelectCardBottomSheet) {
        SelectCardBottomSheet(
            onCardClick = { company ->
                viewModel.updateCardName(company)
                viewModel.hideBottomSheet()
            }
        )
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { Text(uiState.inputInValidMessage) }
        },
        topBar = {
            NewCardTopBar(
                modifier = Modifier,
                onBackClick = { backDispatcher?.onBackPressed() },
                onSaveClick = viewModel::registerCard,
                title = "카드 추가",
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconContentDescription = "뒤로 가기",
                actionIcon = Icons.Filled.Check,
                actionIconContentDescription = "완료"
            )
        },
        content = { innerPadding ->
            NewCardScreen(
                modifier = modifier.padding(innerPadding),
                uiState = uiState,
                setCardNumber = viewModel::setCardNumber,
                setExpiredDate = viewModel::setExpiredDate,
                setOwnerName = viewModel::setOwnerName,
                setPassword = viewModel::setPassword,
            )
        })
}

@Composable
private fun NewCardScreen(
    uiState: NewCardUiState,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 24.dp)

    ) {
        Spacer(modifier = Modifier.height(14.dp))

        PaymentCard(
            creditCardType = CreditCardType.AddingCard(uiState.selectedCard),
            modifier = Modifier.padding(horizontal = 52.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = uiState.cardNumber,
            onValueChange = setCardNumber,
            label = { Text("카드 번호") },
            placeholder = { Text("0000 - 0000 - 0000 - 0000") },
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = uiState.expiredDate,
            onValueChange = setExpiredDate,
            label = { Text("만료일") },
            placeholder = { Text("MM / YY") },
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = uiState.ownerName,
            onValueChange = setOwnerName,
            label = { Text("카드 소유자 이름(선택)") },
            placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = uiState.password,
            onValueChange = setPassword,
            label = { Text("비밀번호") },
            placeholder = { Text("0000") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
        )
    }

}

@Preview
@Composable
private fun StatelessNewCardScreenPreview() {
    val uiState = NewCardUiState(
        cardAdded = false,
        cardNumber = "1234 - 5678 - 9012 - 3456",
        expiredDate = "12 / 25",
        ownerName = "홍길동",
        password = "1234",
        selectedCard = CardCompany(R.drawable.ic_kakao, "카카오뱅크", 0xF444444),
        showSelectCardBottomSheet = false,
        inputInValidMessage = "delicata"
    )
    PaymentsTheme {
        NewCardScreen(
            uiState = uiState,
            setCardNumber = {},
            setExpiredDate = {},
            setOwnerName = {},
            setPassword = {},
        )
    }
}
