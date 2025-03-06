package nextstep.payments.screens.card.mapper

import nextstep.payments.domain.Card
import nextstep.payments.screens.card.update.CardState

fun CardState.toDomain(): Card? {
    return Card(
        cardCompany = selectedCardCompany?.toDomain() ?: return null,
        numbers = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
    )
}

fun Card.toState(): CardState {
    return CardState(
        selectedCardCompany = cardCompany.toState(),
        cardNumber = numbers,
        expiredDate = expiredDate,
        ownerName = ownerName,
    )
}
