package nextstep.payments.data

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import nextstep.payments.R

sealed class CardCompany(
    @StringRes val name: Int,
    val logo: Int,
    @ColorRes val brandColor: Int,
    val bankType: BankType
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

data object EmptyCard : CardCompany(
    name = R.string.empty_card,
    logo = 0,
    brandColor = R.color.empty_card,
    bankType = BankType.NOT_SELECTED
)

data object BcCard : CardCompany(
    name = R.string.bc_card,
    logo = R.drawable.ic_bc_card,
    brandColor = R.color.bc_card,
    bankType = BankType.BC
)

data object ShinhanCard : CardCompany(
    name = R.string.shinhan_card,
    logo = R.drawable.ic_shinhan_card,
    brandColor = R.color.shinhan_card,
    bankType = BankType.SHINHAN
)

data object KakaoBank : CardCompany(
    name = R.string.kakao_bank,
    logo = R.drawable.ic_kakao_bank,
    brandColor = R.color.kakao_bank,
    bankType = BankType.KAKAO
)

data object HyundaiCard : CardCompany(
    name = R.string.hyundai_card,
    logo = R.drawable.ic_hyundai_card,
    brandColor = R.color.hyundai_card,
    bankType = BankType.HYUNDAI
)

data object WooriCard : CardCompany(
    name = R.string.woori_card,
    logo = R.drawable.ic_woori_card,
    brandColor = R.color.woori_card,
    bankType = BankType.WOORI
)

data object LotteCard : CardCompany(
    name = R.string.lotte_card,
    logo = R.drawable.ic_lotte_card,
    brandColor = R.color.lotte_card,
    bankType = BankType.LOTTE
)

data object HanaCard : CardCompany(
    name = R.string.hana_card,
    logo = R.drawable.ic_hana_card,
    brandColor = R.color.hana_card,
    bankType = BankType.HANA
)

data object KbCard : CardCompany(
    name = R.string.kb_card,
    logo = R.drawable.ic_kb_card,
    brandColor = R.color.kb_card,
    bankType = BankType.KB
)


enum class BankTdype(
    @StringRes val company: Int,
    @DrawableRes val logo: Int,
    @ColorRes val brandColor: Int,
) {
    BC(
        company = R.string.bc_card,
        logo = R.drawable.ic_bc_card,
        brandColor = R.color.bc_card
    ),
    SHINHAN(
        company = R.string.shinhan_card,
        logo = R.drawable.ic_shinhan_card,
        brandColor = R.color.shinhan_card
    ),
    KAKAO(
        company = R.string.kakao_bank,
        logo = R.drawable.ic_kakao_bank,
        brandColor = R.color.kakao_bank
    ),
    HYUNDAI(
        company = R.string.hyundai_card,
        logo = R.drawable.ic_hyundai_card,
        brandColor = R.color.hyundai_card
    ),
    WOORI(
        company = R.string.woori_card,
        logo = R.drawable.ic_woori_card,
        brandColor = R.color.woori_card
    ),
    LOTTE(
        company = R.string.lotte_card,
        logo = R.drawable.ic_lotte_card,
        brandColor = R.color.lotte_card
    ),
    HANA(
        company = R.string.hana_card,
        logo = R.drawable.ic_hana_card,
        brandColor = R.color.hana_card
    ),
    KB(
        company = R.string.kb_card,
        logo = R.drawable.ic_kb_card,
        brandColor = R.color.kb_card
    )
}
