package nextstep.payments.ui.cardlist

import nextstep.payments.model.card.CreditCard
import nextstep.payments.ui.component.card.CardBankInformation
import nextstep.payments.ui.component.card.CardInformation

internal sealed interface CardListUiState {
    data object Empty : CardListUiState
    data class One(val card: CardInformation) : CardListUiState
    data class Many(val cards: List<CardInformation>) : CardListUiState

    companion object {
        fun from(cards: List<CreditCard>): CardListUiState = when {
            cards.isEmpty() -> Empty
            cards.size == 1 -> One(cards.first().toUi())
            else -> Many(cards.map { it.toUi() })
        }
    }
}

private fun CreditCard.toUi(): CardInformation = CardInformation(
    id = this.id,
    numberFirst = this.cardNumbers[0],
    numberSecond = this.cardNumbers[1],
    ownerName = this.ownerName,
    expirationDate = this.expirationDate,
    bank = CardBankInformation.from(bankType)
)
