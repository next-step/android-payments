package nextstep.payments.ui.form

import nextstep.payments.model.IssuingBank

data class PaymentCardFormState(
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val issuingBank: IssuingBank? = null,
    val onCardNumberChanged: (String) -> Unit,
    val onExpiredDateChanged: (String) -> Unit,
    val onOwnerNameChanged: (String) -> Unit,
    val onPasswordChanged: (String) -> Unit,
)
