package nextstep.payments.ui.screen.newcard

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nextstep.payments.ui.component.CardCompanyModalBottomSheet
import nextstep.payments.ui.component.NewCardTopBar
import nextstep.payments.ui.component.card.PaymentCard
import nextstep.payments.ui.component.text.CreditCardVisualTransformation
import nextstep.payments.ui.component.text.ExpirationDateVisualTransformation
import nextstep.payments.ui.screen.newcard.model.BankTypeModel
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardRoute(
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
    onBackClick: () -> Unit,
    navigateToCardList: () -> Unit,
) {
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val cardAdded by viewModel.cardAdded.collectAsStateWithLifecycle()
    val selectedBank by viewModel.selectedCard.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    var showCardCompanyBottomSheet by rememberSaveable { mutableStateOf(true) }
    val cardCompanyModalBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )

    LaunchedEffect(cardAdded) {
        if (cardAdded) navigateToCardList()
    }

    LaunchedEffect(Unit) {
        viewModel.snackbarMessages.collect { snackbarMessage ->
            snackbarHostState.showSnackbar(snackbarMessage)
        }
    }

    if (showCardCompanyBottomSheet) {
        CardCompanyModalBottomSheet(
            sheetState = cardCompanyModalBottomSheetState,
            bankTypeModelList = remember { BankTypeModel.getCardBrandList() },
            onDismissRequest = {
                showCardCompanyBottomSheet = false
            },
            onCardCompanySelected = {
                showCardCompanyBottomSheet = false
                viewModel.setSelectedCard(it)
            }
        )
    }

    NewCardScreen(
        modifier = modifier,
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        snackbarHostState = snackbarHostState,
        bankType = selectedBank,
        onBackClick = onBackClick,
        onCardClick = {
            showCardCompanyBottomSheet = true
        },
        onSaveClick = viewModel::addCard,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
    )
}

@Composable
internal fun NewCardScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    bankType: BankTypeModel,
    snackbarHostState: SnackbarHostState,
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = { onBackClick() },
                onSaveClick = { onSaveClick() }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
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
                modifier = Modifier.clickable { onCardClick() },
                cardNumber = cardNumber,
                cardOwnerName = ownerName,
                cardExpiredDate = expiredDate,
                bankType = bankType,
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = cardNumber,
                onValueChange = setCardNumber,
                label = { Text("카드 번호") },
                placeholder = { Text("0000 - 0000 - 0000 - 0000") },
                visualTransformation = CreditCardVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = expiredDate,
                onValueChange = setExpiredDate,
                label = { Text("만료일") },
                placeholder = { Text("MM / YY") },
                visualTransformation = ExpirationDateVisualTransformation(),
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
private fun NewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            cardNumber = "1111222233334444",
            expiredDate = "22 / 33",
            ownerName = "이지훈",
            password = "12345678",
            bankType = BankTypeModel.BC,
            snackbarHostState = SnackbarHostState(),
            onSaveClick = {},
            onBackClick = {},
            setCardNumber = {},
            setExpiredDate = {},
            setOwnerName = {},
            setPassword = {},
            onCardClick = {},
        )
    }
}

