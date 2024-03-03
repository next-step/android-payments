package nextstep.payments.data

import nextstep.payments.domain.PaymentCard
import nextstep.payments.domain.PaymentRepository

object CachedPaymentRepository : PaymentRepository {
    private val cachedPayment = mutableListOf<PaymentCard>()
    override suspend fun addPaymentCard(payment: PaymentCard): String {
        cachedPayment.add(payment)
        return payment.id
    }

    override suspend fun getPayments(): List<PaymentCard> {
        return cachedPayment
    }
}
