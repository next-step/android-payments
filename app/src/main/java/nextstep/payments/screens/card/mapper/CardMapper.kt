package nextstep.payments.screens.card.mapper

import nextstep.payments.domain.Card
import nextstep.payments.screens.card.uistate.CardUiState

fun CardUiState.toDomain(): Card? {
    return Card(
        id = id,
        cardCompany = selectedCardCompany?.toDomain() ?: return null,
        numbers = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
    )
}

fun Card.toState(): CardUiState {
    return CardUiState(
        id = id,
        selectedCardCompany = cardCompany.toState(),
        cardNumber = numbers,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
    )
}
