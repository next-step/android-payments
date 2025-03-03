package nextstep.payments.screens.card

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import nextstep.payments.R
import nextstep.payments.ui.theme.BlackHyundai
import nextstep.payments.ui.theme.BlueShinhan
import nextstep.payments.ui.theme.BlueWoori
import nextstep.payments.ui.theme.BrownKb
import nextstep.payments.ui.theme.RedBC
import nextstep.payments.ui.theme.RedLotte
import nextstep.payments.ui.theme.TealHana
import nextstep.payments.ui.theme.YelloKakao

enum class CardCompanyState(
    @StringRes val nameRes: Int,
    @DrawableRes val imageRes: Int,
    val backgroundColor: Color,
) {
    BC(
        nameRes = R.string.card_company_bc,
        imageRes = R.drawable.img_card_company_bc,
        backgroundColor = RedBC,
    ),
    SHINHAN(
        nameRes = R.string.card_company_shinhan,
        imageRes = R.drawable.img_card_company_shinhan,
        backgroundColor = BlueShinhan,
    ),
    KAKAO(
        nameRes = R.string.card_company_kakao,
        imageRes = R.drawable.img_card_company_kakao,
        backgroundColor = YelloKakao,
    ),
    HYUNDAI(
        nameRes = R.string.card_company_hyundai,
        imageRes = R.drawable.img_card_company_hyundai,
        backgroundColor = BlackHyundai,
    ),
    WOORI(
        nameRes = R.string.card_company_woori,
        imageRes = R.drawable.img_card_company_woori,
        backgroundColor = BlueWoori,
    ),
    LOTTE(
        nameRes = R.string.card_company_lotte,
        imageRes = R.drawable.img_card_company_lotte,
        backgroundColor = RedLotte,
    ),
    HANA(
        nameRes = R.string.card_company_hana,
        imageRes = R.drawable.img_card_company_hana,
        backgroundColor = TealHana,
    ),
    KB(
        nameRes = R.string.card_company_kb,
        imageRes = R.drawable.img_card_company_kb,
        backgroundColor = BrownKb,
    )
    ;
}
