package nextstep.payments.edit

import nextstep.payments.model.CreditCard

data class CardEditState(
    val originalCard: CreditCard = CreditCard.emptyCard,
    val editCard: CreditCard = CreditCard.emptyCard
) {
    val saveEnabled: Boolean
        get() = originalCard != editCard
}
