package nextstep.payments.model


data class CreditCard(
    val number: String,
    val dueDate: String,
    val name: String = "",
    val password: String
) {
    companion object {
        val emptyCard = CreditCard(
            number = "",
            dueDate = "",
            password = "",
            name = ""
        )
    }
}
