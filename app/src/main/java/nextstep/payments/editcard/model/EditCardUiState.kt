package nextstep.payments.editcard.model

import nextstep.payments.common.model.Card
import nextstep.payments.common.model.Validation

data class EditCardUiState(
    val card: Card = Card(
        id = -1,
        cardCompany = null,
        cardNumber = "",
        expiredDate = "",
        ownerName = "",
        password = "",
    ),
    val cardCompanyValidation: Validation = Validation.Init,
    val cardNumberValidation: Validation = Validation.Init,
    val expiredDateValidation: Validation = Validation.Init,
    val passwordValidation: Validation = Validation.Init,
) {
    fun validateAllContents(): Validation {
        return when {
            cardCompanyValidation is Validation.Failure -> {
                return cardCompanyValidation
            }

            cardNumberValidation is Validation.Failure -> {
                return cardNumberValidation
            }

            expiredDateValidation is Validation.Failure -> {
                return expiredDateValidation
            }

            passwordValidation is Validation.Failure -> {
                return passwordValidation
            }

            cardCompanyValidation is Validation.Init &&
                    cardNumberValidation is Validation.Init &&
                    expiredDateValidation is Validation.Init &&
                    passwordValidation is Validation.Init -> {
                return Validation.Init
            }

            else -> {
                Validation.Success
            }
        }
    }
}
