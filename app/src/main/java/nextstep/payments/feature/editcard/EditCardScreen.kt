package nextstep.payments.feature.editcard

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import nextstep.payments.feature.editcard.view.EditCardTopBar
import nextstep.payments.feature.newcard.view.BankSelectBottomSheet
import nextstep.payments.model.Card
import nextstep.payments.view.PaymentCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCardScreen(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditCardViewModel = viewModel(),
) {
    val isEdited by viewModel.isEdited.collectAsStateWithLifecycle()
    val card by viewModel.card.collectAsStateWithLifecycle()

    val keyboardController = LocalSoftwareKeyboardController.current

    val modalBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )

    var isBottomSheetVisible by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    EditCardScreen(
        onBackClick = onBackClick,
        onSaveClick = {
            viewModel.editCard()
            onSaveClick()
        },
        card = card,
        isEdited = isEdited,
        onCardNumberChanged = viewModel::setCardNumber,
        onExpiredDateChanged = viewModel::setExpiredDate,
        onOwnerNameChanged = viewModel::setOwnerName,
        onPasswordChanged = viewModel::setPassword,
        onClickPaymentCard = {
            keyboardController?.hide()
            isBottomSheetVisible = true
        },
        modifier = modifier
    )

    if (isBottomSheetVisible) {
        BankSelectBottomSheet(
            modalBottomSheetState = modalBottomSheetState,
            onDismissRequest = {
                isBottomSheetVisible = false
            },
            onClickBank = {
                scope.launch {
                    viewModel.setBankType(it)
                    modalBottomSheetState.hide()
                }.invokeOnCompletion {
                    isBottomSheetVisible = false
                }
            }
        )
    }
}

@Composable
private fun EditCardScreen(
    isEdited: Boolean,
    card: Card,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    onCardNumberChanged: (String) -> Unit,
    onExpiredDateChanged: (String) -> Unit,
    onOwnerNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onClickPaymentCard: (Card) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            EditCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = onSaveClick,
                saveEnabled = isEdited
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
                card = card,
                onClickPaymentCard = onClickPaymentCard,
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = card.number,
                onValueChange = onCardNumberChanged,
                label = { Text("카드 번호") },
                placeholder = { Text("0000 - 0000 - 0000 - 0000") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = card.expiredDate,
                onValueChange = onExpiredDateChanged,
                label = { Text("만료일") },
                placeholder = { Text("MM / YY") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = card.ownerName,
                onValueChange = onOwnerNameChanged,
                label = { Text("카드 소유자 이름(선택)") },
                placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = card.password,
                onValueChange = onPasswordChanged,
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
private fun EditCardScreenPreview() {
    EditCardScreen(
        isEdited = true,
        card = Card.mock,
        onBackClick = {},
        onSaveClick = {},
        onClickPaymentCard = {},
        onCardNumberChanged = {},
        onExpiredDateChanged = {},
        onOwnerNameChanged = {},
        onPasswordChanged = {}
    )
}