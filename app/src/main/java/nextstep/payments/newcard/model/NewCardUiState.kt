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
    val cardCompanyValidation: Validation = Validation.Failure.Error(R.string.card_company_not_nullable_error),
    val cardNumberValidation: Validation = Validation.Failure.Empty,
    val expiredDateValidation: Validation = Validation.Failure.Empty,
    val passwordValidation: Validation = Validation.Failure.Empty
) {
    fun validateAllContents(): Validation {
        return when {
            cardCompanyValidation !is Validation.Success -> {
                return cardCompanyValidation
            }

            cardNumberValidation !is Validation.Success -> {
                return cardNumberValidation
            }

            expiredDateValidation !is Validation.Success -> {
                return expiredDateValidation
            }

            passwordValidation !is Validation.Success -> {
                return passwordValidation
            }

            else -> {
                Validation.Success
            }
        }
    }
}

sealed class Validation {
    sealed class Failure(val msgId: Int) : Validation() {
        data object Empty : Failure(R.string.fill_input_field)
        class Error(id: Int) : Failure(id)
    }

    data object Success : Validation()
}
