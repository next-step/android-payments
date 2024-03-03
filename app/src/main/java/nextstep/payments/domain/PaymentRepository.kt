package nextstep.payments.domain

interface PaymentRepository {
    suspend fun addPaymentCard(payment: PaymentCard): String
    suspend fun getPayments(): List<PaymentCard>
}
