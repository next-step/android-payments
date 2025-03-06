package nextstep.payments.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import nextstep.payments.R
import nextstep.payments.model.CardCompany
import nextstep.payments.ui.theme.Black
import nextstep.payments.ui.theme.Brown
import nextstep.payments.ui.theme.CherryRed
import nextstep.payments.ui.theme.DeepBlue
import nextstep.payments.ui.theme.Green
import nextstep.payments.ui.theme.OrangeRed
import nextstep.payments.ui.theme.SkyBlue
import nextstep.payments.ui.theme.Yellow

data class CardCompanyResource(
    @StringRes val stringRes: Int,
    @DrawableRes val iconRes: Int,
    val backgroundColor: Color
)

fun CardCompany.toResource(): CardCompanyResource? {
    return when (this) {
        CardCompany.BC -> CardCompanyTokens.bc
        CardCompany.SHINHAN -> CardCompanyTokens.shinhan
        CardCompany.KAKAO -> CardCompanyTokens.kakao
        CardCompany.HYUNDAI -> CardCompanyTokens.hyundai
        CardCompany.WOORI -> CardCompanyTokens.woori
        CardCompany.LOTTE -> CardCompanyTokens.lotte
        CardCompany.HANA -> CardCompanyTokens.hana
        CardCompany.KOOKMIN -> CardCompanyTokens.kookmin
        CardCompany.NONE -> null
    }
}

object CardCompanyTokens {

    val bc = CardCompanyResource(
        stringRes = R.string.card_company_bc,
        iconRes = R.drawable.icon_bc,
        backgroundColor = OrangeRed
    )

    val shinhan = CardCompanyResource(
        stringRes = R.string.card_company_sh,
        iconRes = R.drawable.icon_shinhan,
        backgroundColor = DeepBlue
    )

    val kakao = CardCompanyResource(
        stringRes = R.string.card_company_ka,
        iconRes = R.drawable.icon_kakao,
        backgroundColor = Yellow
    )

    val hyundai = CardCompanyResource(
        stringRes = R.string.card_company_hy,
        iconRes = R.drawable.icon_hyundai,
        backgroundColor = Black
    )

    val woori = CardCompanyResource(
        stringRes = R.string.card_company_wo,
        iconRes = R.drawable.icon_woori,
        backgroundColor = SkyBlue
    )

    val lotte = CardCompanyResource(
        stringRes = R.string.card_company_lo,
        iconRes = R.drawable.icon_lotte,
        backgroundColor = CherryRed
    )

    val hana = CardCompanyResource(
        stringRes = R.string.card_company_ha,
        iconRes = R.drawable.icon_hana,
        backgroundColor = Green
    )

    val kookmin = CardCompanyResource(
        stringRes = R.string.card_company_kb,
        iconRes = R.drawable.icon_kb,
        backgroundColor = Brown
    )

}