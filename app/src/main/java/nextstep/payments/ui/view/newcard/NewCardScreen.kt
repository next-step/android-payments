package nextstep.payments.ui.view.newcard

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import nextstep.payments.component.CardCompanySelector
import nextstep.payments.component.PaymentCard
import nextstep.payments.enums.CardCompanyCategory

@Composable
fun NewCardScreen(
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
) {
    val isEdit by remember { mutableStateOf(viewModel.isEdit) }
    val cardCompanyCategory by viewModel.cardCompanyCategory.collectAsStateWithLifecycle()
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val activity = LocalContext.current as? Activity
    var showCardCompanySelectBottomSheet by remember {
        mutableStateOf(cardCompanyCategory == null)
    }

    LaunchedEffect(viewModel) {
        viewModel.finishEvent.collectLatest {
            activity?.finish()
        }
    }

    NewCardScreen(
        modifier = modifier,
        isEdit = isEdit,
        cardCompanyCategory = cardCompanyCategory,
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        showCardCompanySelectBottomSheet = showCardCompanySelectBottomSheet,
        onCardCompanySelect = {
            viewModel.setCardCompany(it)
            showCardCompanySelectBottomSheet = false
        },
        onCardCompanySelectBottomSheetShowRequest = {
            showCardCompanySelectBottomSheet = true
        },
        onCardCompanySelectBottomSheetDismissRequest = {
            if (cardCompanyCategory == null) {
                activity?.finish()
            }
            showCardCompanySelectBottomSheet = false
        },
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        onBackClick = { activity?.finish() },
        onSaveClick = viewModel::saveCard,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewCardScreen(
    isEdit: Boolean,
    cardCompanyCategory: CardCompanyCategory?,
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    showCardCompanySelectBottomSheet: Boolean,
    onCardCompanySelect: (CardCompanyCategory) -> Unit,
    onCardCompanySelectBottomSheetShowRequest: () -> Unit,
    onCardCompanySelectBottomSheetDismissRequest: () -> Unit,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (showCardCompanySelectBottomSheet) {
        ModalBottomSheet(
            containerColor = Color.White,
            onDismissRequest = onCardCompanySelectBottomSheetDismissRequest,
        ) {
            CardCompanySelector(onCardCompanySelect = onCardCompanySelect)
        }
    }

    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = onSaveClick,
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
                modifier = if (isEdit) {
                    Modifier
                } else {
                    Modifier.clickable(onClick = onCardCompanySelectBottomSheetShowRequest)
                },
                // apply를 사용해서 해봤는데 isEdit이 false일 때 click 처리가 안되는데 이유가 궁금합니다.
//                modifier = Modifier.apply {
//                    if (!isEdit) {
//                        clickable(onClick = onCardCompanySelectBottomSheetShowRequest)
//                    }
//                },
                cardCompanyCategory = cardCompanyCategory,
                cardNumber = cardNumber,
                expiredDate = expiredDate,
                ownerName = ownerName,
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = cardNumber,
                onValueChange = setCardNumber,
                label = { Text("카드 번호") },
                placeholder = { Text("0000 - 0000 - 0000 - 0000") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
            )

            OutlinedTextField(
                value = expiredDate,
                onValueChange = setExpiredDate,
                label = { Text("만료일") },
                placeholder = { Text("MM / YY") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
            )

            OutlinedTextField(
                value = ownerName,
                onValueChange = setOwnerName,
                label = { Text("카드 소유자 이름(선택)") },
                placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
            )

            OutlinedTextField(
                value = password,
                onValueChange = setPassword,
                label = { Text("비밀번호") },
                placeholder = { Text("0000") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { onSaveClick() }
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewCardScreenPreviewStateless() {
    NewCardScreen(
        isEdit = false,
        cardCompanyCategory = CardCompanyCategory.KAKAOBANK,
        cardNumber = "1234 - 5678 - 1234 - 5678",
        expiredDate = "12 / 23",
        ownerName = "홍길동",
        password = "1234",
        showCardCompanySelectBottomSheet = true,
        onCardCompanySelect = {},
        onCardCompanySelectBottomSheetShowRequest = {},
        onCardCompanySelectBottomSheetDismissRequest = {},
        setCardNumber = {},
        setExpiredDate = {},
        setOwnerName = {},
        setPassword = {},
        onBackClick = {},
        onSaveClick = {},
    )
}
