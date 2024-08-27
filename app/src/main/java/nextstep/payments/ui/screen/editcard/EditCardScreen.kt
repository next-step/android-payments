package nextstep.payments.ui.screen.editcard

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
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.R
import nextstep.payments.ui.component.CardCompanyModalBottomSheet
import nextstep.payments.ui.component.card.PaymentCard
import nextstep.payments.ui.component.text.CreditCardVisualTransformation
import nextstep.payments.ui.component.text.ExpirationDateVisualTransformation
import nextstep.payments.ui.component.topbar.PaymentsDefaultTopBar
import nextstep.payments.ui.screen.newcard.model.BankTypeModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCardRoute(
    viewModel: EditCardViewModel,
    eventSink: (EditCardEvent) -> Unit,
    onBackPressed: () -> Unit,
    onSaved: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val sheetState = rememberModalBottomSheetState()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.saved) {
        if (state.saved) {
            onSaved()
        }
    }

    LaunchedEffect(state.backPressed) {
        if (state.backPressed) {
            onBackPressed()
        }
    }

    LaunchedEffect(state.message) {
        state.message?.let {
            val result = snackbarHostState.showSnackbar(it)
            when (result) {
                SnackbarResult.Dismissed -> viewModel.handleEvent(EditCardEvent.OnDismissSnackbar)
                SnackbarResult.ActionPerformed -> Unit
            }
        }
    }

    if (state.showChangeBankType) {
        CardCompanyModalBottomSheet(
            sheetState = sheetState,
            bankTypeModelList = state.cardBrands,
            onDismissRequest = {
                eventSink(EditCardEvent.OnDismissChangeBackType)
            },
            onCardCompanySelected = {
                eventSink(EditCardEvent.OnBankTypeChanged(it))
            }
        )
    }

    EditCardScreen(
        modifier = modifier,
        cardNumber = state.cardNumber,
        expiredDate = state.expiredDate,
        ownerName = state.ownerName,
        password = state.password,
        bankType = state.bankType,
        snackbarHostState = snackbarHostState,
        eventSink = eventSink,
    )
}

@Composable
internal fun EditCardScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    bankType: BankTypeModel?,
    snackbarHostState: SnackbarHostState,
    eventSink: (EditCardEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            PaymentsDefaultTopBar(
                title = stringResource(R.string.edit_card_top_bar_action_icon),
                onBackClick = { eventSink(EditCardEvent.OnBackClicked) },
                onSaveClick = { eventSink(EditCardEvent.OnSaveClicked) }
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
                modifier = Modifier.clickable { eventSink(EditCardEvent.OnCardClicked) },
                cardNumber = cardNumber,
                cardOwnerName = ownerName,
                cardExpiredDate = expiredDate,
                bankType = bankType,
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = cardNumber,
                onValueChange = { eventSink(EditCardEvent.OnCardNumberChanged(it)) },
                label = { Text("카드 번호") },
                placeholder = { Text("0000 - 0000 - 0000 - 0000") },
                visualTransformation = CreditCardVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = expiredDate,
                onValueChange = { eventSink(EditCardEvent.OnExpiredDateChanged(it)) },
                label = { Text("만료일") },
                placeholder = { Text("MM / YY") },
                visualTransformation = ExpirationDateVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = ownerName,
                onValueChange = { eventSink(EditCardEvent.OnOwnerNameChanged(it)) },
                label = { Text("카드 소유자 이름(선택)") },
                placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = password,
                onValueChange = { eventSink(EditCardEvent.OnPasswordChanged(it)) },
                label = { Text("비밀번호") },
                placeholder = { Text("0000") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditCardScreenPreview() {
    EditCardScreen(
        cardNumber = "1111222233334444",
        expiredDate = "22 / 33",
        ownerName = "이지훈",
        password = "12345678",
        bankType = BankTypeModel.BC,
        snackbarHostState = SnackbarHostState(),
        eventSink = {}
    )
}
