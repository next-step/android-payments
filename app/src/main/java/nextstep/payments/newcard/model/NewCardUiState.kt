package nextstep.payments.newcard.model

import nextstep.payments.R
import nextstep.payments.common.model.Card
import nextstep.payments.common.model.Validation


data class NewCardUiState(
    val card: Card = Card(
        id = -1,
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
                cardCompanyValidation
            }

            cardNumberValidation !is Validation.Success -> {
                cardNumberValidation
            }

            expiredDateValidation !is Validation.Success -> {
                expiredDateValidation
            }

            passwordValidation !is Validation.Success -> {
                passwordValidation
            }

            else -> {
                Validation.Success
            }
        }
    }
}
