package nextstep.payments.screens.card.update

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.screens.card.uistate.CardCompanyUiState

@Composable
fun UpdateCardScreen(
    onBackClick: () -> Unit,
    viewModel: UpdateCardViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState: UpdateCardUiState by viewModel.uiState.collectAsStateWithLifecycle()

    UpdateCardScreen(
        uiState = uiState,
        onCardCompanyClick = viewModel::setSelectedCardCompany,
        onCardNumberChange = viewModel::setCardNumber,
        onExpiredDateChange = viewModel::setExpiredDate,
        onOwnerNameChange = viewModel::setOwnerName,
        onPasswordChange = viewModel::setPassword,
        onBackClick = onBackClick,
        onSaveClick = viewModel::updateCard,
        modifier = modifier,
    )
}

@Composable
fun UpdateCardScreen(
    uiState: UpdateCardUiState,
    onCardCompanyClick: (CardCompanyUiState) -> Unit,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        is UpdateCardUiState.Add -> AddCardScreen(
            uiState = uiState,
            onCardCompanyClick = onCardCompanyClick,
            onCardNumberChange = onCardNumberChange,
            onExpiredDateChange = onExpiredDateChange,
            onOwnerNameChange = onOwnerNameChange,
            onPasswordChange = onPasswordChange,
            onBackClick = onBackClick,
            onSaveClick = onSaveClick,
            modifier = modifier
        )

        is UpdateCardUiState.Edit -> EditCardScreen(
            uiState = uiState,
            onCardCompanyClick = onCardCompanyClick,
            onCardNumberChange = onCardNumberChange,
            onExpiredDateChange = onExpiredDateChange,
            onOwnerNameChange = onOwnerNameChange,
            onPasswordChange = onPasswordChange,
            onBackClick = onBackClick,
            onSaveClick = onSaveClick,
            modifier = modifier
        )
    }
}
