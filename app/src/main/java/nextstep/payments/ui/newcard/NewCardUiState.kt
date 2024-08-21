package nextstep.payments.ui.newcard

import nextstep.payments.model.Brand
import nextstep.payments.model.OwnerNameValidResult

data class NewCardUiState(
    val brand: Brand,
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val ownerNameValidResult: OwnerNameValidResult,
) {
    companion object {
        val NONE =
            NewCardUiState(
                brand = Brand.NONE,
                cardNumber = "",
                expiredDate = "",
                ownerName = "",
                password = "",
                ownerNameValidResult = OwnerNameValidResult.NONE,
            )
    }
}
