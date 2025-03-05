package nextstep.payments.newcard.model

import androidx.annotation.StringRes

data class NewCardUiState(
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    @StringRes val showToast: Int? = null,
)
