package nextstep.payments.data

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import nextstep.payments.R

sealed class CardCompany(
    @StringRes val name: Int,
    @DrawableRes val logo: Int,
    @ColorRes val brandColor: Int
)

val cardCompanies = listOf(
    BcCard,
    ShinhanCard,
    KakaoBank,
    HyundaiCard,
    WooriCard,
    LotteCard,
    HanaCard,
    KbCard
)

data object BcCard : CardCompany(
    name = R.string.bc_card,
    logo = R.drawable.ic_bc_card,
    brandColor = R.color.bc_card
)

data object ShinhanCard : CardCompany(
    name = R.string.shinhan_card,
    logo = R.drawable.ic_shinhan_card,
    brandColor = R.color.shinhan_card
)

data object KakaoBank : CardCompany(
    name = R.string.kakao_bank,
    logo = R.drawable.ic_kakao_bank,
    brandColor = R.color.kakao_bank
)

data object HyundaiCard : CardCompany(
    name = R.string.hyundai_card,
    logo = R.drawable.ic_hyundai_card,
    brandColor = R.color.hyundai_card
)

data object WooriCard : CardCompany(
    name = R.string.woori_card,
    logo = R.drawable.ic_woori_card,
    brandColor = R.color.woori_card
)

data object LotteCard : CardCompany(
    name = R.string.lotte_card,
    logo = R.drawable.ic_lotte_card,
    brandColor = R.color.lotte_card
)

data object HanaCard : CardCompany(
    name = R.string.hana_card,
    logo = R.drawable.ic_hana_card,
    brandColor = R.color.hana_card
)

data object KbCard : CardCompany(
    name = R.string.kb_card,
    logo = R.drawable.ic_kb_card,
    brandColor = R.color.kb_card
)
