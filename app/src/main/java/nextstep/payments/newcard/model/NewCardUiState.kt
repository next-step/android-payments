package nextstep.payments.newcard.model

import nextstep.payments.R
import nextstep.payments.common.model.Card


data class NewCardUiState(
    val card: Card = Card(
        cardCompany = null,
        cardNumber = "",
        expiredDate = "",
        ownerName = "",
        password = "",
    ),
    val cardNumberValidation: Validation = Validation.Error(R.string.card_number_length_error),
    val expiredDateValidation: Validation = Validation.Error(R.string.date_length_error),
    val passwordValidation: Validation = Validation.Error(R.string.password_length_error)
)

sealed class Validation {
    data object Success : Validation()
    data class Error(val msgId: Int) : Validation()
}
