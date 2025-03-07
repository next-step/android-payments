package nextstep.payments.ui.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import nextstep.payments.R
import nextstep.payments.data.model.BankType
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.ui.component.CardInputScreen

@Composable
internal fun CardAddScreen(
    cardAddViewModel: CardAddViewModel,
    onBackClick: () -> Unit,
    onSaveCard: () -> Unit,
    onCardAddFailed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val card by cardAddViewModel.card.collectAsStateWithLifecycle()
    val cardAdded by cardAddViewModel.cardAdded.collectAsStateWithLifecycle()
    var bankSelectSheetOpened by remember { mutableStateOf(true) }

    LaunchedEffect(cardAdded) {
        if (cardAdded) onSaveCard()
    }

    LaunchedEffect(Unit) {
        cardAddViewModel.cardAddFailed
            .onEach { onCardAddFailed() }
            .launchIn(this)
    }

    CardInputScreen(
        title = stringResource(R.string.add_card),
        cardNumber = card.number,
        expiredDate = card.expiredDate,
        ownerName = card.ownerName,
        password = card.password,
        bankType = card.bankType,
        bankSelectOpened = bankSelectSheetOpened,
        setBankSelectOpened = { bankSelectSheetOpened = it },
        setCardNumber = cardAddViewModel::setCardNumber,
        setExpiredDate = cardAddViewModel::setExpiredDate,
        setOwnerName = cardAddViewModel::setOwnerName,
        setPassword = cardAddViewModel::setPassword,
        setBankType = cardAddViewModel::setBankType,
        onBackClick = onBackClick,
        onSaveClick = cardAddViewModel::addCard,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun StatefulCardAddScreenPreview() {
    CardAddScreen(
        cardAddViewModel = CardAddViewModel(PaymentCardsRepository).apply {
            setCardNumber("00001111222233333")
            setExpiredDate("0000")
            setOwnerName("홍길동")
            setPassword("0000")
            setBankType(BankType.WOORI)
        },
        onBackClick = {},
        onSaveCard = {},
        onCardAddFailed = {}
    )
}
