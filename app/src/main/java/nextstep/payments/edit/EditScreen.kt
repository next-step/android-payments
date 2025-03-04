package nextstep.payments.edit

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import nextstep.payments.edit.component.EditTopBar
import nextstep.payments.model.BankType
import nextstep.payments.newcard.model.BankTypeUiModel
import nextstep.payments.ui.component.PaymentCard

@Composable
fun EditScreen(
    popBackStack: () -> Unit,
    popBackStackWithResult: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest {
            when (it) {
                is EditSideEffect.NavigateBack -> popBackStack()
                is EditSideEffect.NavigateBackWithNeedReload -> popBackStackWithResult()
            }
        }
    }

    EditScreen(
        cardNumber = state.cardNumber,
        expiredDate = state.expiredDate,
        ownerName = state.ownerName,
        password = state.password,
        bankType = state.bankType,
        isDataChanged = state.card != viewModel.getSavedCard(),
        sendEvent = viewModel::sendEvent,
        modifier = modifier,
    )
}

@Composable
fun EditScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    bankType: BankType,
    isDataChanged: Boolean,
    sendEvent: (EditEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            EditTopBar(
                isDataChanged = isDataChanged,
                sendEvent = sendEvent,
            )
        },
        modifier = modifier,
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
                onValueChange = { sendEvent(EditEvent.OnCardNumberChange(it)) },
                label = { Text("카드 번호") },
                placeholder = { Text("0000 - 0000 - 0000 - 0000") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("cardNumberTextField"),
            )

            OutlinedTextField(
                value = expiredDate,
                onValueChange = { sendEvent(EditEvent.OnExpiredDateChange(it)) },
                label = { Text("만료일") },
                placeholder = { Text("MM / YY") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = ownerName,
                onValueChange = { sendEvent(EditEvent.OnOwnerNameChange(it)) },
                label = { Text("카드 소유자 이름(선택)") },
                placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = password,
                onValueChange = { sendEvent(EditEvent.OnPasswordChange(it)) },
                label = { Text("비밀번호") },
                placeholder = { Text("0000") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
            )
        }
    }
}