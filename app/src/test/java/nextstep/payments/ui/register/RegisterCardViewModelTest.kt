package nextstep.payments.ui.register

import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import nextstep.payments.model.OwnerNameValidResult
import org.junit.Before
import org.junit.Test

class RegisterCardViewModelTest {
    private lateinit var registerCardViewModel: RegisterCardViewModel

    @Before
    fun setUp() {
        registerCardViewModel = RegisterCardViewModel()
    }

    @Test
    fun 카드_소유주_이름이_30자_이하이면_유효하다() =
        runTest {
            // given
            val ownerName = "123456789012345678901234567890"

            // when
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnOwnerNameChanged(ownerName))

            // then
            registerCardViewModel.uiState.test {
                assertEquals(awaitItem().ownerNameValidResult, OwnerNameValidResult.VALID)
            }
        }

    @Test
    fun 카드_소유주_이름이_30자_초과이면_소유주_이름_길이가_유효하지_않다() =
        runTest {
            // given
            val ownerName = "1234567890123456789012345678901"

            // when
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnOwnerNameChanged(ownerName))

            // then
            registerCardViewModel.uiState.test {
                assertEquals(
                    awaitItem().ownerNameValidResult,
                    OwnerNameValidResult.ERROR_OWNER_NAME_LENGTH,
                )
            }
        }
}
