package nextstep.payments.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    val id: Int = 0,
    val bankType: BankType,
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
) : Parcelable
