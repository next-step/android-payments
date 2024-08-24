package nextstep.payments.ui.register

import nextstep.payments.model.Brand
import nextstep.payments.model.OwnerNameValidResult

data class RegisterCardUiState(
    val brand: Brand,
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val ownerNameValidResult: OwnerNameValidResult,
    val mode: Mode,
    val registerEnabled: Boolean,
) {
    enum class Mode {
        REGISTER,
        MODIFY,
        ;

        fun isRegister() = this == REGISTER
    }

    companion object {
        val NONE =
            RegisterCardUiState(
                brand = Brand.NONE,
                cardNumber = "",
                expiredDate = "",
                ownerName = "",
                password = "",
                ownerNameValidResult = OwnerNameValidResult.NONE,
                mode = Mode.REGISTER,
                registerEnabled = false,
            )
    }
}
