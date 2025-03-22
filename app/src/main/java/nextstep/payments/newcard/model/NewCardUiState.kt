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
