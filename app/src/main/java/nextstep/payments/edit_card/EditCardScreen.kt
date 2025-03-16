package nextstep.payments.edit_card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import nextstep.payments.component.BankSelectBottomSheet
import nextstep.payments.component.CardTopAppBar
import nextstep.payments.component.PaymentListCard
import nextstep.payments.data.BankType
import nextstep.payments.data.Card

@Composable
fun EditCardScreen(
    onBackButtonClick: () -> Unit,
    navigateToCardList: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditCardViewModel = viewModel(),
) {
    val cardUpdated by viewModel.cardUpdated.collectAsStateWithLifecycle()
    val card by viewModel.card.collectAsStateWithLifecycle()
    val isBottomSheetOpen by viewModel.isBottomSheetOpen.collectAsStateWithLifecycle()
    val isCompleteButtonEnabled by remember { derivedStateOf { viewModel.initializedCard != card } }

    LaunchedEffect(cardUpdated) {
        if (cardUpdated) navigateToCardList()
    }

    EditCardScreen(
        card = card,
        isBottomSheetOpen = isBottomSheetOpen,
        isCompleteButtonEnabled = isCompleteButtonEnabled,
        onBackClick = onBackButtonClick,
        updateCard = viewModel::updateCard,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        setBankType = viewModel::setBankType,
        setBottomSheetOpen = viewModel::setBottomSheetOpen,
        modifier = modifier,
    )
}

// 가능한 Stateless 컴포넌트로 리팩터링
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCardScreen(
    card: Card,
    isBottomSheetOpen: Boolean,
    isCompleteButtonEnabled: Boolean,
    onBackClick: () -> Unit,
    updateCard: () -> Unit,
    setCardNumber: (String) -> Unit = {},
    setExpiredDate: (String) -> Unit = {},
    setOwnerName: (String) -> Unit = {},
    setPassword: (String) -> Unit = {},
    setBankType: (BankType) -> Unit = {},
    setBottomSheetOpen: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CardTopAppBar(
                title = "카드 수정",
                onBackClick = onBackClick,
                isCompleteButtonEnabled = isCompleteButtonEnabled,
                onCompleteClick = updateCard
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            PaymentListCard(
                modifier = Modifier.clickable {
                    setBottomSheetOpen(true)
                },
                card = card,
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = card.cardNumber,
                onValueChange = setCardNumber,
                label = { Text("카드 번호") },
                placeholder = { Text("0000 - 0000 - 0000 - 0000") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = card.expiredDate,
                onValueChange = setExpiredDate,
                label = { Text("만료일") },
                placeholder = { Text("MM / YY") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = card.ownerName,
                onValueChange = setOwnerName,
                label = { Text("카드 소유자 이름(선택)") },
                placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = card.password,
                onValueChange = setPassword,
                label = { Text("비밀번호") },
                placeholder = { Text("0000") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
            )
        }
    }

    if (isBottomSheetOpen) {
        BankSelectBottomSheet(
            stateSheet = modalBottomSheetState,
            selectBank = {
                setBankType(it)

                scope.launch {
                    modalBottomSheetState.hide()
                }.invokeOnCompletion {
                    setBottomSheetOpen(false)
                }
            },
            onDismissRequest = { setBottomSheetOpen(false) },
        )
    }
}

@Preview
@Composable
private fun StatefulEditCardScreenPreview() {
    val card = Card(
        id = 0,
        cardNumber = "0000 - 0000 - 0000 - 0000",
        expiredDate = "00 / 00",
        ownerName = "홍길동",
        password = "0000",
        bankType = BankType.BC
    )

    EditCardScreen(
        onBackButtonClick = {},
        navigateToCardList = {},
        viewModel = EditCardViewModel(card),
    )
}

@Preview
@Composable
private fun StatelessEditCardScreenPreview() {
    val card = Card(
        id = 0,
        cardNumber = "0000 - 0000 - 0000 - 0000",
        expiredDate = "00 / 00",
        ownerName = "홍길동",
        password = "0000",
        bankType = BankType.BC
    )

    EditCardScreen(
        card = card,
        isBottomSheetOpen = false,
        isCompleteButtonEnabled = false,
        updateCard = {},
        onBackClick = {},
        setCardNumber = {},
        setExpiredDate = {},
        setOwnerName = {},
        setPassword = {},
        setBottomSheetOpen = {},
        setBankType = {},
    )
}