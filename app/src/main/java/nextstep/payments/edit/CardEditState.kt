package nextstep.payments.edit

import nextstep.payments.model.CreditCard
import nextstep.payments.util.CreditCardValidator

data class CardEditState(
    val originalCard: CreditCard = CreditCard.emptyCard,
    val editCard: CreditCard = CreditCard.emptyCard
) {
    val nameError: Boolean
        get() = CreditCardValidator.validateName(editCard.name).not()

    val numberError: Boolean
        get() = CreditCardValidator.validateNumber(editCard.number).not()

    val dueDateError: Boolean
        get() = CreditCardValidator.validateDueDate(editCard.dueDate).not()

    val passwordError: Boolean
        get() = CreditCardValidator.validatePassword(editCard.password).not()

    val saveEnabled: Boolean
        get() = originalCard != editCard
                && nameError.not() && numberError.not()
                && dueDateError.not() && passwordError.not()
}
