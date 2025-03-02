package nextstep.payments.newcard.model

import androidx.annotation.DrawableRes
import nextstep.payments.R
import nextstep.payments.model.BankType

enum class BankTypeUiModel(
    val bankType: BankType,
    val title: String,
    @DrawableRes val iconResId: Int,
) {
    KB(BankType.KB, "국민카드", R.drawable.bank_kb),
    BC(BankType.BC, "BC카드", R.drawable.bank_bc),
    WOORI(BankType.WOORI, "우리카드", R.drawable.bank_woori),
    SHINHAN(BankType.SHINHAN, "신한카드", R.drawable.bank_sinhan),
    KAKAOBANK(BankType.KAKAOBANK, "카카오뱅크", R.drawable.bank_kakaobank),
    HYUNDAI(BankType.HYUNDAI, "현대카드", R.drawable.bank_hyundai),
    LOTTE(BankType.LOTTE, "롯데카드", R.drawable.bank_lotte),
    HANA(BankType.HANA, "하나카드", R.drawable.bank_hana);

    companion object {
        fun from(bankType: BankType): BankTypeUiModel {
            return entries.first { it.bankType == bankType }
        }
    }
}