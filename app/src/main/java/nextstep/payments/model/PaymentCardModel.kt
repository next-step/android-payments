package nextstep.payments.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import nextstep.payments.enums.CardCompanyCategory

@Parcelize
data class PaymentCardModel(
    val cardCompanyCategory: CardCompanyCategory,
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
): Parcelable
