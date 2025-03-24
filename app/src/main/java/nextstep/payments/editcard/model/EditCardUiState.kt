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
                cardCompanyValidation
            }

            cardNumberValidation is Validation.Failure -> {
                cardNumberValidation
            }

            expiredDateValidation is Validation.Failure -> {
                expiredDateValidation
            }

            passwordValidation is Validation.Failure -> {
                passwordValidation
            }

            cardCompanyValidation is Validation.Init &&
                    cardNumberValidation is Validation.Init &&
                    expiredDateValidation is Validation.Init &&
                    passwordValidation is Validation.Init -> {
                Validation.Init
            }

            else -> {
                Validation.Success
            }
        }
    }
}
