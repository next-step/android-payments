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

    @Test
    fun 패스워드_4글자_초과_입력할_수_없다() {
        runTest {
            // given
            registerCardViewModel =
                RegisterCardViewModel(
                    savedStateHandle = savedStateHandle,
                )
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnPasswordChanged("1234"))

            // when
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnPasswordChanged("12345"))

            // then
            registerCardViewModel.uiState.test {
                assertEquals(awaitItem().password.length, 4)
            }
        }
    }

    @Test
    fun 유효한_카드_정보를_모두_입력했다면_등록_버튼이_활성화된다() =
        runTest {
            // given
            registerCardViewModel =
                RegisterCardViewModel(
                    savedStateHandle = savedStateHandle,
                )

            // when
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnCardNumberChanged("1234567890123456"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnExpiredDateChanged("1234"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnOwnerNameChanged("홍길동"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnPasswordChanged("1234"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnBrandSelected(Brand.BC))

            // then
            registerCardViewModel.uiState.test {
                assertEquals(awaitItem().registerEnabled, true)
            }
        }

    @Test
    fun 소유주_이름_입력을_제외한_카드_정보를_모두_입력했다면_등록_버튼이_활성화된다() =
        runTest {
            // given
            registerCardViewModel =
                RegisterCardViewModel(
                    savedStateHandle = savedStateHandle,
                )

            // when
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnCardNumberChanged("1234567890123456"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnExpiredDateChanged("1234"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnPasswordChanged("1234"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnBrandSelected(Brand.BC))

            // then
            registerCardViewModel.uiState.test {
                assertEquals(awaitItem().registerEnabled, true)
            }
        }

    @Test
    fun 카드_번호_12자_미만인_경우_등록_버튼이_활성화된다() =
        runTest {
            // given
            registerCardViewModel =
                RegisterCardViewModel(
                    savedStateHandle = savedStateHandle,
                )

            // when
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnCardNumberChanged("123456789012"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnExpiredDateChanged("1234"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnPasswordChanged("1234"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnBrandSelected(Brand.BC))

            // then
            registerCardViewModel.uiState.test {
                assertEquals(awaitItem().registerEnabled, false)
            }
        }

    @Test
    fun 만료일_4자_미만인_경우_등록_버튼이_비활성화된다() =
        runTest {
            // given
            registerCardViewModel =
                RegisterCardViewModel(
                    savedStateHandle = savedStateHandle,
                )

            // when
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnCardNumberChanged("1234567890123456"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnExpiredDateChanged("123"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnOwnerNameChanged("홍길동"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnPasswordChanged("1234"))

            // then
            registerCardViewModel.uiState.test {
                assertEquals(awaitItem().registerEnabled, false)
            }
        }

    @Test
    fun 소유주_이름_30자_초과인_경우_등록_버튼이_비활성화된다() =
        runTest {
            // given
            registerCardViewModel =
                RegisterCardViewModel(
                    savedStateHandle = savedStateHandle,
                )

            // when
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnCardNumberChanged("1234567890123456"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnExpiredDateChanged("1234"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnOwnerNameChanged("1234567890123456789012345678901"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnPasswordChanged("1234"))

            // then
            registerCardViewModel.uiState.test {
                assertEquals(awaitItem().registerEnabled, false)
            }
        }

    @Test
    fun 패스워드_4자_미만인_경우_등록_버튼이_비활성화된다() =
        runTest {
            // given
            registerCardViewModel =
                RegisterCardViewModel(
                    savedStateHandle = savedStateHandle,
                )

            // when
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnCardNumberChanged("1234567890123456"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnExpiredDateChanged("1234"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnOwnerNameChanged("홍길동"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnPasswordChanged("123"))

            // then
            registerCardViewModel.uiState.test {
                assertEquals(awaitItem().registerEnabled, false)
            }
        }

    @Test
    fun 브랜드_미설정_시_등록_버튼이_비활성화된다() =
        runTest {
            // given
            registerCardViewModel =
                RegisterCardViewModel(
                    savedStateHandle = savedStateHandle,
                )

            // when
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnCardNumberChanged("1234567890123456"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnExpiredDateChanged("1234"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnOwnerNameChanged("홍길동"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnPasswordChanged("1234"))

            // then
            registerCardViewModel.uiState.test {
                assertEquals(awaitItem().registerEnabled, false)
            }
        }

    @Test
    fun 카드_정보_수정_시_카드_정보_변경이_없으면_버튼이_비활성화_된다() =
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

            // when
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnCardNumberChanged("1234567812345678"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnExpiredDateChanged("1234"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnOwnerNameChanged("홍길동"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnPasswordChanged("1234"))

            // then
            registerCardViewModel.uiState.test {
                assertEquals(awaitItem().registerEnabled, false)
            }
        }

    @Test
    fun 카드_정보_수정_시_카드_정보_변경이_있으면_버튼이_활성화_된다() =
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

            // when
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnCardNumberChanged("1234567812345678"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnExpiredDateChanged("1234"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnOwnerNameChanged("홍길동"))
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnPasswordChanged("4567"))

            // then
            registerCardViewModel.uiState.test {
                assertEquals(awaitItem().registerEnabled, true)
            }
        }

    @Test
    fun 카드_번호_16자_초과_입력할_수_없다() {
        runTest {
            // given
            registerCardViewModel =
                RegisterCardViewModel(
                    savedStateHandle = savedStateHandle,
                )
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnCardNumberChanged("1234567812345678"))

            // when
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnCardNumberChanged("12345678123456781"))

            // then
            registerCardViewModel.uiState.test {
                assertEquals(awaitItem().cardNumber.length, 16)
            }
        }
    }

    @Test
    fun 만료일_4자_초과_입력할_수_없다() {
        runTest {
            // given
            registerCardViewModel =
                RegisterCardViewModel(
                    savedStateHandle = savedStateHandle,
                )
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnExpiredDateChanged("1234"))

            // when
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnExpiredDateChanged("12345"))

            // then
            registerCardViewModel.uiState.test {
                assertEquals(awaitItem().expiredDate.length, 4)
            }
        }
    }

    @Test
    fun 비밀번호_4자_초과_입력할_수_없다() {
        runTest {
            // given
            registerCardViewModel =
                RegisterCardViewModel(
                    savedStateHandle = savedStateHandle,
                )
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnPasswordChanged("1234"))

            // when
            registerCardViewModel.dispatchEvent(RegisterCardScreenEvent.OnPasswordChanged("12345"))

            // then
            registerCardViewModel.uiState.test {
                assertEquals(awaitItem().password.length, 4)
            }
        }
    }
}
