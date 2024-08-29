package nextstep.payments.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Card(
    val id: String = UUID.randomUUID().toString(),
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val cardCompany: String,
    val cardColor: Int,
    val bankType: BankType
) : Parcelable
