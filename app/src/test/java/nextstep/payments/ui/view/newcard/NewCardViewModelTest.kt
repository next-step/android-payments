package nextstep.payments.ui.view.newcard

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import nextstep.payments.enums.CardCompanyCategory
import nextstep.payments.model.PaymentCardModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NewCardViewModelTest {

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    @Test
    fun `카드추가에서 소유자를 제외하고 나머지가 입력되면 저장 가능`() = runTest {
        // Given
        val viewModel = NewCardViewModel(savedStateHandle = SavedStateHandle())
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.canSave.collect()
        }

        // When
        viewModel.setCardCompany(CardCompanyCategory.KAKAOBANK)
        viewModel.setCardNumber("1234-1234-1234-1234")
        viewModel.setExpiredDate("12/23")
        viewModel.setOwnerName("석준")
        viewModel.setPassword("1234")

        // Then
        assertEquals(viewModel.canSave.value, true)
    }

    @Test
    fun `카드수정에서 변경사항이 없으면 저장 불가능`() = runTest {
        // Given
        val paymentCard = PaymentCardModel(
            cardCompanyCategory = CardCompanyCategory.KAKAOBANK,
            cardNumber = "1234-1234-1234-1234",
            expiredDate = "12/23",
            ownerName = "석준",
            password = "1234"
        )
        val viewModel = NewCardViewModel(savedStateHandle = SavedStateHandle(mapOf("paymentCard" to paymentCard)))
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.canSave.collect()
        }

        // When
        // nothing

        // Then
        assertEquals(viewModel.canSave.value, false)
    }


    @Test
    fun `카드수정에서 변경사항이 있으면 저장 불가능`() = runTest {
        // Given
        val paymentCard = PaymentCardModel(
            cardCompanyCategory = CardCompanyCategory.KAKAOBANK,
            cardNumber = "1234-1234-1234-1234",
            expiredDate = "12/23",
            ownerName = "석준",
            password = "1234"
        )
        val viewModel = NewCardViewModel(savedStateHandle = SavedStateHandle(mapOf("paymentCard" to paymentCard)))
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.canSave.collect()
        }

        // When
        viewModel.setPassword("5678")

        // Then
        assertEquals(viewModel.canSave.value, true)
    }
}
