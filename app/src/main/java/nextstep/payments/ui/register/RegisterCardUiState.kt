package nextstep.payments.ui.register

import nextstep.payments.model.Brand
import nextstep.payments.model.ExpiredDateMonthValidResult
import nextstep.payments.model.OwnerNameValidResult

sealed class RegisterCardUiState(
    open val brand: Brand,
    open val cardNumber: String,
    open val expiredDate: String,
    open val ownerName: String,
    open val password: String,
    open val ownerNameValidResult: OwnerNameValidResult,
    open val expiredDateMonthValidResult: ExpiredDateMonthValidResult,
    open val registerEnabled: Boolean,
) {
    val isRegister: Boolean
        get() = this is Register

    fun copyState(
        brand: Brand = this.brand,
        cardNumber: String = this.cardNumber,
        expiredDate: String = this.expiredDate,
        ownerName: String = this.ownerName,
        password: String = this.password,
        ownerNameValidResult: OwnerNameValidResult = this.ownerNameValidResult,
        expiredDateMonthValidResult: ExpiredDateMonthValidResult = this.expiredDateMonthValidResult,
        registerEnabled: Boolean = this.registerEnabled,
    ): RegisterCardUiState =
        when (this) {
            is Register -> {
                this.copy(
                    brand = brand,
                    cardNumber = cardNumber,
                    expiredDate = expiredDate,
                    ownerName = ownerName,
                    password = password,
                    ownerNameValidResult = ownerNameValidResult,
                    expiredDateMonthValidResult = expiredDateMonthValidResult,
                    registerEnabled = registerEnabled,
                )
            }

            is Update -> {
                this.copy(
                    brand = brand,
                    cardNumber = cardNumber,
                    expiredDate = expiredDate,
                    ownerName = ownerName,
                    password = password,
                    ownerNameValidResult = ownerNameValidResult,
                    expiredDateMonthValidResult = expiredDateMonthValidResult,
                    registerEnabled = registerEnabled,
                )
            }
        }

    data class Register(
        override val brand: Brand,
        override val cardNumber: String,
        override val expiredDate: String,
        override val ownerName: String,
        override val password: String,
        override val ownerNameValidResult: OwnerNameValidResult,
        override val expiredDateMonthValidResult: ExpiredDateMonthValidResult,
        override val registerEnabled: Boolean,
    ) : RegisterCardUiState(
            brand = brand,
            cardNumber = cardNumber,
            expiredDate = expiredDate,
            ownerName = ownerName,
            password = password,
            ownerNameValidResult = ownerNameValidResult,
            expiredDateMonthValidResult = expiredDateMonthValidResult,
            registerEnabled = registerEnabled,
        )

    data class Update(
        override val brand: Brand,
        override val cardNumber: String,
        override val expiredDate: String,
        override val ownerName: String,
        override val password: String,
        override val ownerNameValidResult: OwnerNameValidResult,
        override val expiredDateMonthValidResult: ExpiredDateMonthValidResult,
        override val registerEnabled: Boolean,
    ) : RegisterCardUiState(
            brand = brand,
            cardNumber = cardNumber,
            expiredDate = expiredDate,
            ownerName = ownerName,
            password = password,
            ownerNameValidResult = ownerNameValidResult,
            expiredDateMonthValidResult = expiredDateMonthValidResult,
            registerEnabled = registerEnabled,
        )

    companion object {
        val DEFAULT_REGISTER =
            Register(
                brand = Brand.NONE,
                cardNumber = "",
                expiredDate = "",
                ownerName = "",
                password = "",
                ownerNameValidResult = OwnerNameValidResult.NONE,
                expiredDateMonthValidResult = ExpiredDateMonthValidResult.NONE,
                registerEnabled = false,
            )
    }
}
