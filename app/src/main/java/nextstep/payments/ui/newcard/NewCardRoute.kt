package nextstep.payments.ui.newcard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NewCardRoute(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    viewModel: NewCardViewModel = viewModel(),
) {
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val isValidCard by viewModel.isValidCard.collectAsStateWithLifecycle(initialValue = false)

    NewCardScreen(
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        setCardNumber = viewModel::setCardNumber,
        setPassword = viewModel::setPassword,
        setOwnerName = viewModel::setOwnerName,
        setExpiredDate = viewModel::setExpiredDate,
        isValidCard = isValidCard,
        onBackClick = onBackClick,
        onSaveClick = {
            viewModel.addCard()
            onSaveClick()
        },
    )
}
