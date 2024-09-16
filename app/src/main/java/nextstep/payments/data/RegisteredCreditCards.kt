package nextstep.payments.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import nextstep.payments.ui.card.CreditCardUiState

@Parcelize
data class RegisteredCreditCards(val cardList: List<Card>) : Parcelable {

    fun getState(): CreditCardUiState {
        return when (cardList.size) {
            0 -> CreditCardUiState.Empty
            1 -> CreditCardUiState.One(cardList[0])
            else -> CreditCardUiState.Many(cardList)
        }
    }
}
