package nextstep.payments.ui.newcard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.designsystem.component.CardInfoScreen
import nextstep.payments.designsystem.theme.PaymentsTheme
import nextstep.payments.model.BankType
import nextstep.payments.ui.newcard.component.NewCardTopBar

@Composable
fun NewCardScreen(
    onBackClick: () -> Unit,
    onRouteToCardList: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.cardAdded) {
        if (uiState.cardAdded) {
            onRouteToCardList()
        }
    }

    CardInfoScreen(
        cardNumber = uiState.cardNumber,
        expiredDate = uiState.expiredDate,
        ownerName = uiState.ownerName,
        password = uiState.password,
        bankType = uiState.selectedBank,
        topBar = { NewCardTopBar(onBackClick = onBackClick, onSaveClick = viewModel::addCard) },
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        setBankType = viewModel::setBankType,
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
private fun NewCardScreenScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            onBackClick = {},
            onRouteToCardList = {},
            viewModel = NewCardViewModel().apply {
                setCardNumber("0000000000000000")
                setExpiredDate("0000")
                setOwnerName("홍길동")
                setPassword("0000")
                setBankType(BankType.KB)
            })
    }
}
