package nextstep.payments.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import nextstep.payments.R

enum class BankType(
    @StringRes val bankName: Int,
    @DrawableRes val image: Int,
    val backgroundColor: Color
) {
    NOT_SELECTED(R.string.bank_name_empty, 0, Color(0xFF333333)),
    BC(R.string.bank_bc, R.drawable.ic_bank_bc, Color(0xFFf04651)),
    SHINHAN(R.string.bank_shinhan, R.drawable.ic_bank_shinhan, Color(0xFF2246ff)),
    KAKAO(R.string.bank_kakao, R.drawable.ic_bank_kakao, Color(0xFFfce600)),
    HYUNDAI(R.string.bank_hyundai, R.drawable.ic_bank_hyundai, Color(0xFF000000)),
    WOORI(R.string.bank_woori, R.drawable.ic_bank_woori, Color(0xFF247bc8)),
    LOTTE(R.string.bank_lotte, R.drawable.ic_bank_lotte, Color(0xFFed1c24)),
    HANA(R.string.bank_hana, R.drawable.ic_bank_hana, Color(0xFF259490)),
    KB(R.string.bank_kb, R.drawable.ic_bank_kb, Color(0xFF695F54));

    companion object {
        fun getSelectBanks(): List<BankType> = entries.filter { it != NOT_SELECTED }
    }
}