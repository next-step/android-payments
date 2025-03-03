package nextstep.payments.screens.card

import nextstep.payments.domain.CardCompany

fun CardCompanyState.toDomain(): CardCompany = when (this) {
    CardCompanyState.BC -> CardCompany.BC
    CardCompanyState.SHINHAN -> CardCompany.SHINHAN
    CardCompanyState.KAKAO -> CardCompany.KAKAO
    CardCompanyState.HYUNDAI -> CardCompany.HYUNDAI
    CardCompanyState.WOORI -> CardCompany.WOORI
    CardCompanyState.LOTTE -> CardCompany.LOTTE
    CardCompanyState.HANA -> CardCompany.HANA
    CardCompanyState.KB -> CardCompany.KB
}

fun CardCompany.toState(): CardCompanyState = when (this) {
    CardCompany.BC -> CardCompanyState.BC
    CardCompany.SHINHAN -> CardCompanyState.SHINHAN
    CardCompany.KAKAO -> CardCompanyState.KAKAO
    CardCompany.HYUNDAI -> CardCompanyState.HYUNDAI
    CardCompany.WOORI -> CardCompanyState.WOORI
    CardCompany.LOTTE -> CardCompanyState.LOTTE
    CardCompany.HANA -> CardCompanyState.HANA
    CardCompany.KB -> CardCompanyState.KB
}
