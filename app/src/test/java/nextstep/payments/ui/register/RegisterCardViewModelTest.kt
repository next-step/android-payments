package nextstep.payments.ui.register

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.model.Brand
import nextstep.payments.model.Card
import nextstep.payments.model.OwnerNameValidResult
import nextstep.payments.ui.register.navigation.ARG_CARD_ID
import org.junit.Before
import org.junit.Test

class RegisterCardViewModelTest {
    private lateinit var registerCardViewModel: RegisterCardViewModel
    private val savedStateHandle = SavedStateHandle()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun 카드_소유주_이름이_30자_이하이면_유효하다() =
        runTest {
            // given
            val ownerName = "123456789012345678901234567890"
            registerCardViewModel =
                RegisterCardViewModel(
                    savedStateHandle = savedStateHandle,
                )

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
            registerCardViewModel =
                RegisterCardViewModel(
                    savedStateHandle = savedStateHandle,
                )

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

    @Test
    fun 신규_카드_등록을_할_수_있다() {
        runTest {
            // given
            registerCardViewModel =
                RegisterCardViewModel(
                    savedStateHandle = savedStateHandle,
                )

            // when & then
            registerCardViewModel.uiState.test {
                assertEquals(awaitItem().mode, RegisterCardUiState.Mode.REGISTER)
            }
        }
    }

    @Test
    fun 카드_정보_수정을_할_수_있다() {
        runTest {
            // given
            val cardId: Long = 1
            PaymentCardsRepository.addCard(
                Card(
                    id = cardId,
                    brand = Brand.BC,
                    cardNumber = "1234567812345678",
                    ownerName = "홍길동",
                    expiredDate = "1234",
                    password = "1234",
                ),
            )
            savedStateHandle[ARG_CARD_ID] = cardId.toString()
            registerCardViewModel =
                RegisterCardViewModel(
                    savedStateHandle = savedStateHandle,
                )

            // when & then
            registerCardViewModel.uiState.test {
                assertEquals(awaitItem().mode, RegisterCardUiState.Mode.MODIFY)
            }
        }
    }

    @Test
    fun 신규_카드_등록_후_카드목록_화면으로_이동한다() =
        runTest {
            // given
            registerCardViewModel =
                RegisterCardViewModel(
                    savedStateHandle = savedStateHandle,
                )

            registerCardViewModel.effect.test {
                // when - 카드 정보 입력 후 카드 등록 버튼 클릭
                registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnCardNumberChanged("1234567890123456"))
                registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnExpiredDateChanged("1234"))
                registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnOwnerNameChanged("홍길동"))
                registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnPasswordChanged("1234"))
                registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnRegisterCardClicked)

                // then - 카드 목록 화면으로 이동
                assertEquals(awaitItem(), RegisterCardScreenEffect.NavigateToCardListScreen(true))
            }
        }
}
