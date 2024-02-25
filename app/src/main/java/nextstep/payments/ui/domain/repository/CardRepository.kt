package nextstep.payments.ui.domain.repository

import nextstep.payments.ui.domain.model.Card
import nextstep.payments.ui.domain.model.CardRegistrationForm

interface CardRepository {

    suspend fun getCardList(): List<Card>

    suspend fun addCard(form: CardRegistrationForm)
}
