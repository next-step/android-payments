package nextstep.payments.screens.card

import nextstep.payments.domain.CardCompany

fun CardCompanyState.toDomain() = CardCompany(
    id = id,
    name = name,
)
