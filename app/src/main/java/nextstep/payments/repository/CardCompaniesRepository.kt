package nextstep.payments.repository

import nextstep.payments.R
import nextstep.payments.model.CardCompany

object CardCompaniesRepository {
    val data = listOf(
        CardCompany(logo = R.drawable.bc, name = "BC카드"),
        CardCompany(logo = R.drawable.shinhan, name = "신한카드"),
        CardCompany(logo = R.drawable.kakaobank, name = "카카오뱅크"),
        CardCompany(logo = R.drawable.hyundai, name = "현대카드"),
        CardCompany(logo = R.drawable.woori, name = "우리카드"),
        CardCompany(logo = R.drawable.lotte, name = "롯데카드"),
        CardCompany(logo = R.drawable.hana, name = "하나카드"),
        CardCompany(logo = R.drawable.kb, name = "국민카드")
    )
}
