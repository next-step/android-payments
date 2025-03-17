package nextstep.payments.new_card

import nextstep.payments.model.CreditCard
import nextstep.payments.util.CreditCardValidator

data class NewCardState(
    val card: CreditCard = CreditCard.emptyCard,
) {
    val nameError: Boolean
        get() = CreditCardValidator.validateName(card.name).not()

    val numberError: Boolean
        get() = CreditCardValidator.validateNumber(card.number).not()

    val dueDateError: Boolean
        get() = CreditCardValidator.validateDueDate(card.dueDate).not()

    val passwordError: Boolean
        get() = CreditCardValidator.validatePassword(card.password).not()

    val saveEnabled: Boolean
        get() = nameError.not() && numberError.not() && dueDateError.not() && passwordError.not()
}
