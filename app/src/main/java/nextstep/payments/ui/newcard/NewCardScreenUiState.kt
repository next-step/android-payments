package nextstep.payments.ui.newcard

import nextstep.payments.model.OwnerNameValidResult

data class NewCardScreenUiState(
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val ownerNameValidResult: OwnerNameValidResult,
) {
    companion object {
        val NONE = NewCardScreenUiState(
            cardNumber = "",
            expiredDate = "",
            ownerName = "",
            password = "",
            ownerNameValidResult = OwnerNameValidResult.NONE,
        )
    }
}
