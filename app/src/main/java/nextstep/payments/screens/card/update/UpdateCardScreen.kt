package nextstep.payments.screens.card.update

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.screens.card.state.CardCompanyState
import nextstep.payments.screens.card.state.CardState

@Composable
fun UpdateCardScreen(
    cardState: CardState?,
    navigateToCardList: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateCardViewModel = viewModel(),
) {
    val uiState: UpdateCardUiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.cardUpdated) {
        if (uiState.cardUpdated) navigateToCardList()
    }

    LaunchedEffect(Unit) {
        if (cardState != null) {
            viewModel.setEditCardMode(cardState)
        }
    }

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
    onCardCompanyClick: (CardCompanyState) -> Unit,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        is UpdateCardUiState.AddCardUiState -> AddCardScreen(
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

        is UpdateCardUiState.EditCardUiState -> EditCardScreen(
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
