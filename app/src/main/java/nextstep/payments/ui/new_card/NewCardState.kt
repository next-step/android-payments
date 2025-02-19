package nextstep.payments.ui.new_card

import androidx.compose.ui.text.input.TextFieldValue

data class NewCardState(
    val cardNumber: TextFieldValue = TextFieldValue(),
    val expiredDate: TextFieldValue = TextFieldValue(),
    val ownerName: String = "",
    val password: String = "",
)
