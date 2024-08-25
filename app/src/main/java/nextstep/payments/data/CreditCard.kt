package nextstep.payments.data

data class CreditCard(val cardList: List<Card>) {

    fun getState(): CreditCardUiState {
        return when (cardList.size) {
            0 -> CreditCardUiState.Empty
            1 -> CreditCardUiState.One(cardList[0])
            else -> CreditCardUiState.Many(cardList)
        }
    }
}
