package nextstep.payments.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import nextstep.payments.model.BankType
import nextstep.payments.newcard.component.BankSelectBottomSheet
import nextstep.payments.newcard.component.NewCardTopBar
import nextstep.payments.newcard.model.BankTypeUiModel
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardScreen(
    popBackStack: () -> Unit,
    popBackStackWithResult: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val bankSelectBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest {
            when (it) {
                is NewCardSideEffect.NavigateBack -> popBackStack()
                is NewCardSideEffect.NavigateBackWithNeedReload -> popBackStackWithResult()
                is NewCardSideEffect.HideBottomSheet -> {
                    bankSelectBottomSheetState.hide()
                    viewModel.sendEvent(NewCardEvent.OnHideBottomSheet)
                }
            }
        }
    }

    NewCardScreen(
        cardNumber = state.cardNumber,
        expiredDate = state.expiredDate,
        ownerName = state.ownerName,
        password = state.password,
        bankType = state.bankType,
        isShowBottomSheet = state.isShowBottomSheet,
        bankSelectBottomSheetState = bankSelectBottomSheetState,
        sendEvent = viewModel::sendEvent,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewCardScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    bankType: BankType,
    isShowBottomSheet: Boolean,
    sendEvent: (NewCardEvent) -> Unit,
    modifier: Modifier = Modifier,
    bankSelectBottomSheetState: SheetState = rememberModalBottomSheetState(),
) {
    Scaffold(
        topBar = { NewCardTopBar(sendEvent) },
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
                bankType = BankTypeUiModel.from(bankType),
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = cardNumber,
                onValueChange = { sendEvent(NewCardEvent.OnCardNumberChange(it)) },
                label = { Text("카드 번호") },
                placeholder = { Text("0000 - 0000 - 0000 - 0000") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = expiredDate,
                onValueChange = { sendEvent(NewCardEvent.OnExpiredDateChange(it)) },
                label = { Text("만료일") },
                placeholder = { Text("MM / YY") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = ownerName,
                onValueChange = { sendEvent(NewCardEvent.OnOwnerNameChange(it)) },
                label = { Text("카드 소유자 이름(선택)") },
                placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = password,
                onValueChange = { sendEvent(NewCardEvent.OnPasswordChange(it)) },
                label = { Text("비밀번호") },
                placeholder = { Text("0000") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
            )
        }

        if (isShowBottomSheet) {
            BankSelectBottomSheet(
                sendEvent = sendEvent,
                sheetState = bankSelectBottomSheetState,
                modifier = Modifier.testTag("카드사 선택 바텀 시트"),
            )
        }
    }
}

@Preview
@Composable
private fun StatefulNewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            viewModel = NewCardViewModel(),
            popBackStack = {},
            popBackStackWithResult = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun StatelessNewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            cardNumber = "0000 - 0000 - 0000 - 0000",
            expiredDate = "2025-02-19",
            ownerName = "홍순동",
            password = "12345",
            bankType = BankType.LOTTE,
            isShowBottomSheet = false,
            sendEvent = {},
        )
    }
}
