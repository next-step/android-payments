package nextstep.payments.data.model

import kotlinx.serialization.Serializable
import nextstep.payments.ui.CardCompanyType

@Serializable
data class Card(
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val cardCompanyType: CardCompanyType,
)
