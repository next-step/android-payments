package nextstep.payments.screens.card

import nextstep.payments.CardCompanyState
import nextstep.payments.domain.CardCompany

fun CardCompanyState.toDomain() = CardCompany(
    id = id,
    name = name,
)
