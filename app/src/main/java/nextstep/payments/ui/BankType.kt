package nextstep.payments.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import nextstep.payments.R
import nextstep.payments.ui.theme.BcColor
import nextstep.payments.ui.theme.DefaultBankColor
import nextstep.payments.ui.theme.HanaColor
import nextstep.payments.ui.theme.HyundaiColor
import nextstep.payments.ui.theme.KBColor
import nextstep.payments.ui.theme.KakaoColor
import nextstep.payments.ui.theme.LotteColor
import nextstep.payments.ui.theme.ShinhanColor
import nextstep.payments.ui.theme.WooriColor

enum class BankType(
    @StringRes val bankNameResId: Int,
    @DrawableRes val bankImageRes: Int?,
    val bankThemeColor: Color
) {
    NOT_SELECTED(R.string.bank_not_select, null, DefaultBankColor),
    BC(R.string.bank_bc, R.drawable.bc, BcColor),
    SHINHAN(R.string.bank_shinhan, R.drawable.shinhan, ShinhanColor),
    KAKAO(R.string.bank_kakao, R.drawable.kakao, KakaoColor),
    HYUNDAI(R.string.bank_hyundai, R.drawable.hyundai, HyundaiColor),
    WOORI(R.string.bank_woori, R.drawable.woori, WooriColor),
    LOTTE(R.string.bank_lotte, R.drawable.lotte, LotteColor),
    HANA(R.string.bank_hana, R.drawable.hana, HanaColor),
    KB(R.string.bank_kb, R.drawable.kb, KBColor);

    companion object {
        fun getBankList(): List<BankType> = entries.filter { it != NOT_SELECTED }
    }
}
