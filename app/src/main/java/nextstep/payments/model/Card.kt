package nextstep.payments.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    val id: Int,
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val color: Long,
    val cardCompany: String = ""
) : Parcelable {
    companion object {
        private var currentId = 0
        fun autoIncrement(): Int {
            return currentId++
        }
    }

    constructor(
        cardNumber: String,
        expiredDate: String,
        ownerName: String,
        password: String,
        color: Long,
        cardCompany: String = ""
    ) : this(autoIncrement(), cardNumber, expiredDate, ownerName, password, color, cardCompany)
}
