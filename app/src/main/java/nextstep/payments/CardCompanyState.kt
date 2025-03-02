package nextstep.payments

import androidx.compose.ui.graphics.Color
import nextstep.payments.ui.theme.Black100
import nextstep.payments.ui.theme.BlackHyundai
import nextstep.payments.ui.theme.BlueShinhan
import nextstep.payments.ui.theme.BlueWoori
import nextstep.payments.ui.theme.BrownKb
import nextstep.payments.ui.theme.RedBC
import nextstep.payments.ui.theme.RedLotte
import nextstep.payments.ui.theme.TealHana
import nextstep.payments.ui.theme.YelloKakao

enum class CardCompanyState(
    val id: Int,
    val nameRes: Int,
    val imageRes: Int,
    val backgroundColor: Color,
) {
    NOT_SELECTED(
        id = -1,
        nameRes = R.string.card_company_not_selected,
        imageRes = R.drawable.ic_launcher_foreground,
        backgroundColor = Black100,
    ),
    BC(
        id = 1,
        nameRes = R.string.card_company_bc,
        imageRes = R.drawable.img_card_company_bc,
        backgroundColor = RedBC,
    ),
    SHINHAN(
        id = 2,
        nameRes = R.string.card_company_shinhan,
        imageRes = R.drawable.img_card_company_shinhan,
        backgroundColor = BlueShinhan,
    ),
    KAKAO(
        id = 3,
        nameRes = R.string.card_company_kakao,
        imageRes = R.drawable.img_card_company_kakao,
        backgroundColor = YelloKakao,
    ),
    HYUNDAI(
        id = 4,
        nameRes = R.string.card_company_hyundai,
        imageRes = R.drawable.img_card_company_hyundai,
        backgroundColor = BlackHyundai,
    ),
    WOORI(
        id = 5,
        nameRes = R.string.card_company_woori,
        imageRes = R.drawable.img_card_company_woori,
        backgroundColor = BlueWoori,
    ),
    LOTTE(
        id = 6,
        nameRes = R.string.card_company_lotte,
        imageRes = R.drawable.img_card_company_lotte,
        backgroundColor = RedLotte,
    ),
    HANA(
        id = 7,
        nameRes = R.string.card_company_hana,
        imageRes = R.drawable.img_card_company_hana,
        backgroundColor = TealHana,
    ),
    KB(
        id = 8,
        nameRes = R.string.card_company_kb,
        imageRes = R.drawable.img_card_company_kb,
        backgroundColor = BrownKb,
    )
    ;

    companion object {
        fun findCardCompanyById(id: Int): CardCompanyState = entries.find { it.id == id } ?: NOT_SELECTED

        fun getAllCardCompanies(): List<CardCompanyState> = entries.filter { it != NOT_SELECTED }
    }
}
