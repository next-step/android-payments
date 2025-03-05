package nextstep.payments.newcard.model

import nextstep.payments.R


data class NewCardUiState(
    val cardNumber: String = "",
    val cardNumberValidation: Validation = Validation.Error(R.string.card_number_length_error),
    val expiredDate: String = "",
    val expiredDateValidation: Validation = Validation.Error(R.string.date_length_error),
    val ownerName: String = "",
    val password: String = "",
    val passwordValidation: Validation = Validation.Error(R.string.password_length_error)
)

sealed class Validation {
    data object Success : Validation()
    data class Error(val msgId: Int) : Validation()
}
