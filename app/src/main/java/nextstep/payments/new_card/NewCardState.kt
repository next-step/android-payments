package nextstep.payments.new_card

import nextstep.payments.model.CreditCard

data class NewCardState(
    val card: CreditCard = CreditCard.emptyCard,
)