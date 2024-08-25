package nextstep.payments.data

import androidx.compose.ui.graphics.Color

data class Card(
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val cardCompany: String,
    val cardColor: Color,
)
