package nextstep.payments.domain

interface PaymentRepository {
    suspend fun addPaymentCard(payment: PaymentCard)
    suspend fun getPayments(): List<PaymentCard>
}
