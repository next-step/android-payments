package nextstep.payments.data

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import nextstep.payments.R

enum class BankType(
    @StringRes val companyName: Int,
    @DrawableRes val logo: Int,
    @ColorRes val brandColor: Int,
) {
    NOT_SELECTED(
        companyName = R.string.empty_card,
        logo = 0,
        brandColor = R.color.empty_card,
    ),
    BC(
        companyName = R.string.bc_card,
        logo = R.drawable.ic_bc_card,
        brandColor = R.color.bc_card,
    ),
    SHINHAN(
        companyName = R.string.shinhan_card,
        logo = R.drawable.ic_shinhan_card,
        brandColor = R.color.shinhan_card,
    ),
    KAKAO(
        companyName = R.string.kakao_bank,
        logo = R.drawable.ic_kakao_bank,
        brandColor = R.color.kakao_bank,
    ),
    HYUNDAI(
        companyName = R.string.hyundai_card,
        logo = R.drawable.ic_hyundai_card,
        brandColor = R.color.hyundai_card,
    ),
    WOORI(
        companyName = R.string.woori_card,
        logo = R.drawable.ic_woori_card,
        brandColor = R.color.woori_card,
    ),
    LOTTE(
        companyName = R.string.lotte_card,
        logo = R.drawable.ic_lotte_card,
        brandColor = R.color.lotte_card,
    ),
    HANA(
        companyName = R.string.hana_card,
        logo = R.drawable.ic_hana_card,
        brandColor = R.color.hana_card,
    ),
    KB(
        companyName = R.string.kb_card,
        logo = R.drawable.ic_kb_card,
        brandColor = R.color.kb_card,
    )
}
