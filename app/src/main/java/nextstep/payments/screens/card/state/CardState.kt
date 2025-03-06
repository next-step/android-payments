package nextstep.payments.screens.card.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardState(
    val selectedCardCompany: CardCompanyState? = null,
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
) : Parcelable {
    fun isFormValid(): Boolean {
        return cardNumber.isNotBlank() &&
                expiredDate.isNotBlank() &&
                ownerName.isNotBlank() &&
                password.isNotBlank() &&
                selectedCardCompany != null
    }
}
