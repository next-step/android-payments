package nextstep.payments.data.model

import kotlinx.serialization.Serializable
import nextstep.payments.ui.CardCompanyType
import java.util.UUID

@Serializable
data class Card(
    val cardId: String = UUID.randomUUID().toString(),
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val cardCompanyType: CardCompanyType,
)
