package nextstep.payments.newcard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

internal object NewCardRoute {

    @Composable
    operator fun invoke(
        viewModel: NewCardViewModel = viewModel()
    ) {
        val cardDetails by viewModel.cardDetails.collectAsStateWithLifecycle()

        NewCardScreen(
            cardNumber = cardDetails.cardNumber,
            expiredDate = cardDetails.expiredDate,
            ownerName = cardDetails.ownerName ?: "",
            password = cardDetails.password,
            setCardNumber = viewModel::setCardNumber,
            setExpiredDate = viewModel::setExpiredDate,
            setOwnerName = viewModel::setOwnerName,
            setPassword = viewModel::setPassword,
        )
    }
}

