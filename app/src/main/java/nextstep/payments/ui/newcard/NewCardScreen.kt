package nextstep.payments.ui.newcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import nextstep.payments.model.Card
import nextstep.payments.model.CardCompany
import nextstep.payments.ui.common.PaymentCard
import nextstep.payments.ui.newcard.component.CardSelectBottomSheet
import nextstep.payments.ui.newcard.component.NewCardTopBar
import nextstep.payments.ui.theme.PaymentsTheme


// stateful
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NewCardScreen(
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    navigateToCardList: () -> Unit = {}
) {
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val cardAdded by viewModel.cardAdded.collectAsStateWithLifecycle()
    val selectedCard by viewModel.selectedCard.collectAsStateWithLifecycle()
    val cardCompanies by viewModel.cardCompanies.collectAsStateWithLifecycle()
    var showCardCompanyBottomSheet by rememberSaveable { mutableStateOf(viewModel.cardModification == null) }
    val cardCompanyModalBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )
    val canSave by viewModel.canSave.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(cardAdded) {
        if (cardAdded) navigateToCardList()
    }

    if (showCardCompanyBottomSheet) {
        CardSelectBottomSheet(
            sheetState = cardCompanyModalBottomSheetState,
            cardCompanies = cardCompanies,
            onDismissRequest = { showCardCompanyBottomSheet = false },
            onCardClick = {
                viewModel.setCardCompany(it)
            }
        )
    }
    NewCardScreen(
        isModify = viewModel.cardModification != null,
        canSave = canSave,
        cardCompany = selectedCard,
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        onBackClick = onBackClick,
        onChangeCardCompanyClick = {
            showCardCompanyBottomSheet = true
            coroutineScope.launch {
                cardCompanyModalBottomSheetState.show()
            }
        },
        onSaveClick = viewModel::saveCard,
        modifier = modifier
    )
}

// stateless
@Composable
private fun NewCardScreen(
    isModify: Boolean,
    canSave: Boolean,
    cardCompany: CardCompany?,
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: (Card) -> Unit,
    onChangeCardCompanyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = {
                    onSaveClick(
                        Card(
                            cardNumber = cardNumber,
                            expiredDate = expiredDate,
                            ownerName = ownerName,
                            password = password,
                            cardCompany = cardCompany
                        ),
                    )
                },
                title = if (isModify) "카드 수정" else "카드 추가",
                canSave = canSave
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            PaymentCard(
                modifier = Modifier
                    .padding(horizontal = 52.dp)
                    .clickable {
                        onChangeCardCompanyClick()
                    },
                cardCompany = cardCompany,
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = cardNumber,
                onValueChange = { setCardNumber(it) },
                label = { Text("카드 번호") },
                placeholder = { Text("0000-0000-0000-0000") },
                visualTransformation = CardNumberVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = expiredDate,
                onValueChange = { setExpiredDate(it) },
                label = { Text("만료일") },
                placeholder = { Text("MM / YY") },
                visualTransformation = ExpiredDateVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = ownerName,
                onValueChange = { setOwnerName(it) },
                label = { Text("카드 소유자 이름(선택)") },
                placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = password,
                onValueChange = { setPassword(it.take(30)) },
                label = { Text("비밀번호") },
                placeholder = { Text("0000") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = PasswordVisualTransformation(),
            )
        }
    }
}


@Preview(name = "카드 추가 and 저장 버튼 비활성화")
@Composable
private fun NewCardScreenSaveButtonUnablePreview() {
    PaymentsTheme {
        NewCardScreen(
            isModify = false,
            canSave = false,
            cardCompany = CardCompany.KB,
            cardNumber = "1211231122222222",
            expiredDate = "121233",
            ownerName = "컴포즈2",
            password = "0000",
            onBackClick = {},
            onChangeCardCompanyClick = {},
            onSaveClick = {},
            setCardNumber = {},
            setExpiredDate = {},
            setOwnerName = {},
            setPassword = {},
        )
    }
}

@Preview(name = "카드 추가 and 저장 버튼 비활성화")
@Composable
private fun NewCardScreenSaveButtonAblePreview() {
    PaymentsTheme {
        NewCardScreen(
            isModify = false,
            canSave = true,
            cardCompany = CardCompany.KB,
            cardNumber = "1211231122222222",
            expiredDate = "121233",
            ownerName = "컴포즈2",
            password = "0000",
            onBackClick = {},
            onChangeCardCompanyClick = {},
            onSaveClick = {},
            setCardNumber = {},
            setExpiredDate = {},
            setOwnerName = {},
            setPassword = {},
        )
    }
}

@Preview(name = "수정 화면 and 저장 버튼 비활성화")
@Composable
private fun ModifyCardScreenSaveButtonUnablePreview() {
    PaymentsTheme {
        NewCardScreen(
            isModify = true,
            canSave = false,
            cardCompany = CardCompany.KB,
            cardNumber = "1211231122222222",
            expiredDate = "121233",
            ownerName = "컴포즈2",
            password = "0000",
            onBackClick = {},
            onChangeCardCompanyClick = {},
            onSaveClick = {},
            setCardNumber = {},
            setExpiredDate = {},
            setOwnerName = {},
            setPassword = {},
        )
    }
}

@Preview(name = "수정 화면 and 저장 버튼 비활성화")
@Composable
private fun ModifyCardScreenSaveButtonAblePreview() {
    PaymentsTheme {
        NewCardScreen(
            isModify = true,
            canSave = true,
            cardCompany = CardCompany.KB,
            cardNumber = "1211231122222222",
            expiredDate = "121233",
            ownerName = "컴포즈2",
            password = "0000",
            onBackClick = {},
            onChangeCardCompanyClick = {},
            onSaveClick = {},
            setCardNumber = {},
            setExpiredDate = {},
            setOwnerName = {},
            setPassword = {},
        )
    }
}