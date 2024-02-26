package nextstep.payments.data

import nextstep.payments.domain.PaymentCard
import nextstep.payments.domain.PaymentRepository

object CachedPaymentRepository : PaymentRepository {
    private val cachedPayment = mutableListOf<PaymentCard>()
    override suspend fun addPaymentCard(payment: PaymentCard) {
        cachedPayment.add(payment)
    }

    override suspend fun getPayments(): List<PaymentCard> {
        return cachedPayment
    }
}
