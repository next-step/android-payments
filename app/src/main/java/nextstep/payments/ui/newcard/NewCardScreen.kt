package nextstep.payments.ui.newcard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NewCardScreen(
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
) {
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()

    NewCardScreenContent(
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        onCardNumberChanged = viewModel::setCardNumber,
        onExpiredDateChanged = viewModel::setExpiredDate,
        onOwnerNameChanged = viewModel::setOwnerName,
        onPasswordChanged = viewModel::setPassword,
        modifier = modifier
    )
}