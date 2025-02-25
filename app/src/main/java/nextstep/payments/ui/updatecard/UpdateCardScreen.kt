package nextstep.payments.ui.updatecard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.designsystem.component.CardInfoScreen
import nextstep.payments.ui.updatecard.component.UpdateCardTopBar

@Composable
fun UpdateCardScreen(
    onBackClick: () -> Unit,
    onUpdate: () -> Unit,
    viewModel: UpdateCardViewModel,
    modifier: Modifier = Modifier
) {

    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()

    val cardUpdated by viewModel.cardUpdated.collectAsStateWithLifecycle()
    val selectedBank by viewModel.selectedBank.collectAsStateWithLifecycle()

    LaunchedEffect(cardUpdated) {
        if (cardUpdated) {
            onUpdate()
        }
    }

    CardInfoScreen(
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        bankType = selectedBank,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        setBankType = {},
        topBar = {
            UpdateCardTopBar(onSaveClick = viewModel::updateCard, onBackClick = onBackClick)
        },
        modifier = modifier
    )

}