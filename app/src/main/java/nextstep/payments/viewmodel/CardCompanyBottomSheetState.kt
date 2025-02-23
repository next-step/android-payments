package nextstep.payments.viewmodel

sealed interface CardCompanyBottomSheetState {
    data object Show : CardCompanyBottomSheetState
    data object Hide : CardCompanyBottomSheetState
}
