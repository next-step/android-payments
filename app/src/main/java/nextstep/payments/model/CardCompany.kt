package nextstep.payments.model

import androidx.annotation.DrawableRes
import nextstep.payments.R

enum class CardCompany(
    val companyName: String,
    @DrawableRes val iconResource: Int,
    val backgroundColor: Long,
) {
    BC(
        "BC카드",
        R.drawable.ic_bc,
        0xFFFF3348,
    ),
    SHINHAN(
        "신한카드",
        R.drawable.ic_shinhan,
        0XFF0046FF,
    ),
    KAKAOBANK(
        "카카오뱅크",
        R.drawable.ic_kakaobank,
        0xFFFFE300,
    ),
    HYUNDAI(
        "현대카드",
        R.drawable.ic_hyundai,
        0xFF000000,
    ),
    WOORI(
        "우리카드",
        R.drawable.ic_woori,
        0xFF0078B9,
    ),
    LOTTE(
        "롯데카드",
        R.drawable.ic_lotte,
        0xFFDA291C,
    ),
    HANA(
        "하나카드",
        R.drawable.ic_hana,
        0xFF008485,
    ),
    KB(
        "국민카드",
        R.drawable.ic_kb,
        0xFF695F54,
    ),
    NOT_SELECTED(
        "", 0, 0xFF333333
    );
    companion object {
        val activeCards: List<CardCompany> = entries.filter { it != NOT_SELECTED }
    }
}