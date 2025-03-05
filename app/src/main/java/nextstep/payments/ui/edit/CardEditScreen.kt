package nextstep.payments.ui.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.R
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.ui.component.CardInputScreen

@Composable
internal fun CardEditScreen(
    cardEditViewModel: CardEditViewModel,
    onBackClick: () -> Unit,
    onSaveCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val card by cardEditViewModel.card.collectAsStateWithLifecycle()
    val cardUpdated by cardEditViewModel.cardUpdated.collectAsStateWithLifecycle()

    LaunchedEffect(cardUpdated) {
        if (cardUpdated) onSaveCard()
    }

    CardInputScreen(
        title = stringResource(R.string.edit_card),
        cardNumber = card.number,
        expiredDate = card.expiredDate,
        ownerName = card.ownerName,
        password = card.password,
        bankType = card.bankType,
        sheetOpened = false,
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
        onSaveCard = {}
    )
}
