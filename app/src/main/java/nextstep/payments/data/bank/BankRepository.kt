package nextstep.payments.data.bank

import nextstep.payments.data.Bank

interface BankRepository {
    fun getBanks(): List<Bank>
}