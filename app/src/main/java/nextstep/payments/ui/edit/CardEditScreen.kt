package nextstep.payments.ui.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import nextstep.payments.R
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.ui.component.CardFormScreen

@Composable
internal fun CardEditScreen(
    cardEditViewModel: CardEditViewModel,
    onBackClick: () -> Unit,
    onSaveCard: () -> Unit,
    onCardUpdateFailed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val card by cardEditViewModel.card.collectAsStateWithLifecycle()
    val cardUpdated by cardEditViewModel.cardUpdated.collectAsStateWithLifecycle()
    var bankSelectSheetOpened by remember { mutableStateOf(false) }

    LaunchedEffect(cardUpdated) {
        if (cardUpdated) onSaveCard()
    }

    LaunchedEffect(Unit) {
        cardEditViewModel.cardUpdateFailed
            .onEach { onCardUpdateFailed() }
            .launchIn(this)
    }

    CardFormScreen(
        title = stringResource(R.string.edit_card),
        cardNumber = card.number,
        expiredDate = card.expiredDate,
        ownerName = card.ownerName,
        password = card.password,
        bankType = card.bankType,
        bankSelectOpened = bankSelectSheetOpened,
        setBankSelectOpened = { bankSelectSheetOpened = it },
        setCardNumber = cardEditViewModel::setCardNumber,
        setExpiredDate = cardEditViewModel::setExpiredDate,
        setOwnerName = cardEditViewModel::setOwnerName,
        setPassword = cardEditViewModel::setPassword,
        setBankType = cardEditViewModel::setBankType,
        onBackClick = onBackClick,
        onSaveClick = cardEditViewModel::updateCard,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun CardEditScreenPreview() {
    CardEditScreen(
        cardEditViewModel = CardEditViewModel(SavedStateHandle(), PaymentCardsRepository),
        onBackClick = {},
        onSaveCard = {},
        onCardUpdateFailed = {}
    )
}
