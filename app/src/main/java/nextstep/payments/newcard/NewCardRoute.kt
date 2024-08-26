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
        val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
        val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
        val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
        val password by viewModel.password.collectAsStateWithLifecycle()

        NewCardScreen(
            cardNumber = cardNumber,
            expiredDate = expiredDate,
            ownerName = ownerName,
            password = password,
            setCardNumber = viewModel::setCardNumber,
            setExpiredDate = viewModel::setExpiredDate,
            setOwnerName = viewModel::setOwnerName,
            setPassword = viewModel::setPassword,
        )
    }
}

