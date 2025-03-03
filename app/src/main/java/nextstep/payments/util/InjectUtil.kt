package nextstep.payments.util

import androidx.compose.runtime.Composable
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.ui.cardlist.CardListViewModel
import nextstep.payments.ui.updatecard.UpdateCardViewModel

object InjectUtil {

    @Composable
    fun createCardListViewModel() : CardListViewModel {
        return viewModel(factory = createCardListViewModelFactory())
    }

    @Composable
    fun createUpdateCardViewModel() : UpdateCardViewModel {
        return viewModel(factory = createUpdateCardViewModelFactory())
    }


    private fun createCardListViewModelFactory(repository: PaymentCardsRepository = PaymentCardsRepository) =
        object : AbstractSavedStateViewModelFactory() {
            override fun <T : ViewModel> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
                if (modelClass.isAssignableFrom(CardListViewModel::class.java)) {
                    return CardListViewModel(handle, repository) as T
                } else throw IllegalArgumentException()
            }
        }

    private fun createUpdateCardViewModelFactory(repository: PaymentCardsRepository = PaymentCardsRepository) =
        object : AbstractSavedStateViewModelFactory() {
            override fun <T : ViewModel> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
                if (modelClass.isAssignableFrom(UpdateCardViewModel::class.java)) {
                    return UpdateCardViewModel(handle, repository) as T
                } else throw IllegalArgumentException()
            }
        }

}