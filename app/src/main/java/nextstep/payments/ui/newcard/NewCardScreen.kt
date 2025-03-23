package nextstep.payments.ui.newcard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.NewCardViewModel
import nextstep.payments.model.CreditCard

@Composable
fun NewCardScreen(
    viewModel: NewCardViewModel,
    navigateToCardList: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()

    NewCardScreenContent(
        card = CreditCard(
            cardNumber = cardNumber,
            expiredDate = expiredDate,
            ownerName = ownerName,
            password = password
        ),
        onCardNumberChanged = viewModel::setCardNumber,
        onExpiredDateChanged = viewModel::setExpiredDate,
        onOwnerNameChanged = viewModel::setOwnerName,
        onPasswordChanged = viewModel::setPassword,
        modifier = modifier
    )
}