package nextstep.payments.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import nextstep.payments.R
import nextstep.payments.ui.theme.BcColor
import nextstep.payments.ui.theme.HanaColor
import nextstep.payments.ui.theme.HyundaiColor
import nextstep.payments.ui.theme.KBColor
import nextstep.payments.ui.theme.KakaoColor
import nextstep.payments.ui.theme.LotteColor
import nextstep.payments.ui.theme.ShinhanColor
import nextstep.payments.ui.theme.WooriColor

enum class CardCompanyType(
    @StringRes val cardCompanyNameResId: Int,
    @DrawableRes val cardCompanyImageRes: Int?,
    val cardCompanyThemeColor: Color
) {
    BC(R.string.card_company_bc, R.drawable.bc, BcColor),
    SHINHAN(R.string.card_company_shinhan, R.drawable.shinhan, ShinhanColor),
    KAKAO(R.string.card_company_kakao, R.drawable.kakao, KakaoColor),
    HYUNDAI(R.string.card_company_hyundai, R.drawable.hyundai, HyundaiColor),
    WOORI(R.string.card_company_woori, R.drawable.woori, WooriColor),
    LOTTE(R.string.card_company_lotte, R.drawable.lotte, LotteColor),
    HANA(R.string.card_company_hana, R.drawable.hana, HanaColor),
    KB(R.string.card_company_kb, R.drawable.kb, KBColor);

    companion object {
        fun getBankList(): List<CardCompanyType> = entries
    }
}
