package nextstep.payments.model

import java.util.UUID


data class CreditCard(
    val id: String = UUID.randomUUID().toString(),
    val number: String,
    val dueDate: String,
    val name: String = "",
    val password: String,
    val company: CardCompany
) {
    companion object {
        val emptyCard = CreditCard(
            number = "",
            dueDate = "",
            password = "",
            name = "",
            company = CardCompany.NONE
        )
    }
}
