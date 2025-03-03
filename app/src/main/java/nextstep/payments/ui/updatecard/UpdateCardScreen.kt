package nextstep.payments.ui.updatecard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.designsystem.component.CardInfoScreen
import nextstep.payments.ui.updatecard.component.UpdateCardTopBar
import nextstep.payments.util.InjectUtil

@Composable
fun UpdateCardScreen(
    onBackClick: () -> Unit,
    onUpdate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateCardViewModel = InjectUtil.createUpdateCardViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    LaunchedEffect(uiState.cardUpdated) {
        if (uiState.cardUpdated) {
            onUpdate()
        }
    }

    CardInfoScreen(
        cardNumber = uiState.cardNumber,
        expiredDate = uiState.expiredDate,
        ownerName = uiState.ownerName,
        password = uiState.password,
        bankType = uiState.selectedBank,
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