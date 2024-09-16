package nextstep.payments.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    var id: Int = 0,
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val bankType: BankType
) : Parcelable
