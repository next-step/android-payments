package nextstep.payments.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import nextstep.payments.R

enum class BankType(
    @StringRes val bankNameResId: Int?,
    @DrawableRes val bankImageRes: Int?
) {
    NOT_SELECTED(null, null),
    BC(R.string.bank_bc, R.drawable.bc),
    SHINHAN(R.string.bank_shinhan, R.drawable.shinhan),
    KAKAO(R.string.bank_kakao, R.drawable.kakao),
    HYUNDAI(R.string.bank_hyundai, R.drawable.hyundai),
    WOORI(R.string.bank_woori, R.drawable.woori),
    LOTTE(R.string.bank_lotte, R.drawable.lotte),
    HANA(R.string.bank_hana, R.drawable.hana),
    KB(R.string.bank_kb, R.drawable.kukmin);

    companion object {
        fun getBankList(): List<BankType> = entries.filter { it != NOT_SELECTED }
    }
}
