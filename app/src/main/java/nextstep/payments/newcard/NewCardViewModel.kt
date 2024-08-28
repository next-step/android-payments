package nextstep.payments.newcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.model.CreditCard
import nextstep.payments.repository.PaymentCardsRepository

/**
 * NewCardViewModel은 새 결제 카드를 관리하고 추가하는 로직을 포함한 ViewModel입니다.
 * 카드 정보는 PaymentCard 데이터 클래스로 관리되며, UI에서 사용할 상태 값들은 StateFlow를 통해 제공됩니다.
 */
class NewCardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    // 카드를 성공적으로 추가했는지 여부를 나타내는 상태.
    private val _cardAdded = MutableStateFlow(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        _cardNumber.value = cardNumber
    }

    fun setExpiredDate(expiredDate: String) {
        _expiredDate.value = expiredDate
    }

    fun setOwnerName(ownerName: String) {
        _ownerName.value = ownerName
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    /**
     * 현재 입력된 카드 정보를 저장소에 추가합니다.
     * 카드가 성공적으로 추가되면, cardAdded 상태를 true로 변경합니다.
     *
     * UI에서는 cardAdded가 true로 변경되었을 때, 네비게이션이나 이벤트 처리를 할 수 있습니다.
     */
    fun addCard() {
//        repository.addCard(card = _cardDetails.value)
        _cardAdded.value = true
    }
}
