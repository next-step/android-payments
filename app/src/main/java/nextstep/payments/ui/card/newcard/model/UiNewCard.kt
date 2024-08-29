package nextstep.payments.ui.card.newcard.model

import nextstep.payments.model.BankType
import nextstep.payments.model.Card

data class UiNewCard(
    val bankType: BankType?,
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
) {

    companion object {
        fun init(card: Card?) = UiNewCard(
            bankType = card?.bankType,
            cardNumber = card?.cardNumber ?: "",
            expiredDate = card?.expiredDate ?: "",
            ownerName = card?.ownerName ?: "",
            password = card?.password ?: "",
        )
    }

}
