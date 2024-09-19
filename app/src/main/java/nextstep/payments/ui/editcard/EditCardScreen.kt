package nextstep.payments.ui.editcard

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import nextstep.payments.R
import nextstep.payments.model.card.CardNumber
import nextstep.payments.model.card.CreditCard
import nextstep.payments.ui.component.card.CardBankInformation
import nextstep.payments.ui.component.card.CardInformation
import nextstep.payments.ui.component.card.edit.EditableCardPage
import nextstep.payments.ui.theme.PaymentsTheme
import java.time.Month
import java.time.YearMonth
import java.util.UUID

@Composable
internal fun EditCardScreen(
    viewModel: EditCardViewModel,
    onCardUpdated: () -> Unit,
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val targetCardInformation = viewModel.targetCard.toCardInformation()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest {
            snackbarHostState.showSnackbar(message = context.getString(R.string.card_edit_error))
        }
    }
    LaunchedEffect(true) {
        viewModel.cardUpdated.collectLatest { onCardUpdated() }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { contentPadding ->
            EditCardScreen(
                cardNumber = uiState.cardNumber,
                expirationDate = uiState.expirationDate,
                ownerName = uiState.ownerName,
                password = uiState.password,
                cardInformation = targetCardInformation,
                setCardNumber = viewModel::setCardNumber,
                setExpiredDate = viewModel::setExpiredDate,
                setOwnerName = viewModel::setOwnerName,
                setPassword = viewModel::setPassword,
                onBackClick = onBackClick,
                onSaveClick = viewModel::updateCard,
                modifier = Modifier.padding(contentPadding),
            )
        }
    )
}

private fun CreditCard.toCardInformation(): CardInformation = CardInformation(
    id = this.id,
    numberFirst = this.cardNumbers[0],
    numberSecond = this.cardNumbers[1],
    ownerName = this.ownerName,
    expirationDate = this.expirationDate,
    bank = CardBankInformation.from(this.bankType)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EditCardScreen(
    cardNumber: String,
    expirationDate: String,
    ownerName: String,
    password: String,
    cardInformation: CardInformation,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.edit_card_top_bar_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "뒤로 가기",
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            focusManager.clearFocus()
                            onSaveClick()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "완료",
                        )
                    }
                },
            )
        },
        content = { innerPadding ->
            EditableCardPage(
                cardNumber = cardNumber,
                expiredDate = expirationDate,
                ownerName = ownerName,
                password = password,
                cardInformation = cardInformation,
                setCardNumber = setCardNumber,
                setExpiredDate = setExpiredDate,
                setOwnerName = setOwnerName,
                setPassword = setPassword,
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
            )
        }
    )
}

@PreviewLightDark
@Composable
private fun EditCardScreenPreview() {
    PaymentsTheme {
        CardInformation(
            id = UUID.randomUUID(),
            numberFirst = CardNumber("1234"),
            numberSecond = CardNumber("5678"),
            ownerName = "이범석",
            expirationDate = YearMonth.of(2025, Month.JANUARY),
            bank = CardBankInformation.Kakao,
        ).also {
            EditCardScreen(
                cardNumber = "1234567812345678",
                expirationDate = "2501",
                ownerName = "이범석",
                password = "1234",
                cardInformation = it,
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                onBackClick = {},
                onSaveClick = {},
            )
        }
    }
}