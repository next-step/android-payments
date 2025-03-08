package nextstep.payments.screens.card.mapper

import nextstep.payments.domain.Card
import nextstep.payments.screens.card.state.CardState

fun CardState.toDomain(): Card? {
    return Card(
        id = id,
        cardCompany = selectedCardCompany?.toDomain() ?: return null,
        numbers = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
    )
}

fun Card.toState(): CardState {
    return CardState(
        id = id,
        selectedCardCompany = cardCompany.toState(),
        cardNumber = numbers,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
    )
}
