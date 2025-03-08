package nextstep.payments.screens.card.mapper

import nextstep.payments.domain.CardCompany
import nextstep.payments.screens.card.uistate.CardCompanyUiState

fun CardCompanyUiState.toDomain(): CardCompany = when (this) {
    CardCompanyUiState.BC -> CardCompany.BC
    CardCompanyUiState.SHINHAN -> CardCompany.SHINHAN
    CardCompanyUiState.KAKAO -> CardCompany.KAKAO
    CardCompanyUiState.HYUNDAI -> CardCompany.HYUNDAI
    CardCompanyUiState.WOORI -> CardCompany.WOORI
    CardCompanyUiState.LOTTE -> CardCompany.LOTTE
    CardCompanyUiState.HANA -> CardCompany.HANA
    CardCompanyUiState.KB -> CardCompany.KB
}

fun CardCompany.toState(): CardCompanyUiState = when (this) {
    CardCompany.BC -> CardCompanyUiState.BC
    CardCompany.SHINHAN -> CardCompanyUiState.SHINHAN
    CardCompany.KAKAO -> CardCompanyUiState.KAKAO
    CardCompany.HYUNDAI -> CardCompanyUiState.HYUNDAI
    CardCompany.WOORI -> CardCompanyUiState.WOORI
    CardCompany.LOTTE -> CardCompanyUiState.LOTTE
    CardCompany.HANA -> CardCompanyUiState.HANA
    CardCompany.KB -> CardCompanyUiState.KB
}
