package nextstep.payments.ui.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.model.Brand
import nextstep.payments.model.CardRegisterResult
import nextstep.payments.model.ExpiredDateMonthValidResult
import nextstep.payments.model.OwnerNameValidResult
import nextstep.payments.ui.component.BankSelectBottomSheet
import nextstep.payments.ui.component.CardNumberVisualTransformation
import nextstep.payments.ui.component.ExpiredDateVisualTransformation
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.component.PaymentsTopBar
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun RegisterCardRoute(
    navigateToCredit: (CardRegisterResult) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterCardViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect {
            when (it) {
                is RegisterCardScreenEffect.NavigateToCardListScreen -> navigateToCredit(it.result)
            }
        }
    }

    RegisterCardScreen(
        uiState = uiState,
        navigateUp = navigateToCredit,
        onNewCardScreenEvent = viewModel::dispatchEvent,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RegisterCardScreen(
    uiState: RegisterCardUiState,
    navigateUp: (CardRegisterResult) -> Unit,
    onNewCardScreenEvent: (RegisterCardScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedBrand by remember { mutableStateOf(Brand.NONE) }
    var showBottomSheet by remember { mutableStateOf(uiState.isRegister) }
    Scaffold(
        topBar = {
            PaymentsTopBar(
                title = stringResource(id = R.string.title_new_card),
                onBackClick = { navigateUp(CardRegisterResult.FAILED) },
                actions = {
                    IconButton(
                        onClick = { onNewCardScreenEvent(RegisterCardScreenEvent.OnRegisterCardClicked) },
                        enabled = uiState.registerEnabled,
                        modifier = Modifier.testTag("RegisterCardButton"),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = stringResource(id = R.string.content_description_register_card),
                        )
                    }
                },
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .imePadding()
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            if (uiState.isRegister) {
                PaymentCard(
                    brand = uiState.brand,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        showBottomSheet = true
                    },
                )
            } else {
                PaymentCard(
                    brand = uiState.brand,
                    cardNumber = uiState.cardNumber,
                    expiredDate = uiState.expiredDate,
                    ownerName = uiState.ownerName,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = { showBottomSheet = true },
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            CardNumberTextField(
                cardNumber = uiState.cardNumber,
                onNewCardScreenEvent = onNewCardScreenEvent,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag("cardNumber"),
            )

            Spacer(modifier = Modifier.height(18.dp))

            ExpiredDateTextField(
                expiredDate = uiState.expiredDate,
                expiredDateMonthValidResult = uiState.expiredDateMonthValidResult,
                onNewCardScreenEvent = onNewCardScreenEvent,
                modifier =
                    Modifier
                        .width(146.dp)
                        .testTag("expiredDate"),
            )

            OwnerNameTextField(
                ownerName = uiState.ownerName,
                ownerNameValidResult = uiState.ownerNameValidResult,
                onNewCardScreenEvent = onNewCardScreenEvent,
                modifier =
                    modifier
                        .fillMaxWidth()
                        .testTag("ownerName"),
            )

            PasswordTextField(
                password = uiState.password,
                onNewCardScreenEvent = onNewCardScreenEvent,
                modifier =
                    Modifier
                        .width(146.dp)
                        .testTag("password"),
            )
        }
        if (showBottomSheet) {
            BankSelectBottomSheet(
                selectedBrand = selectedBrand,
                onBrandSelected = {
                    selectedBrand = it
                    onNewCardScreenEvent(RegisterCardScreenEvent.OnBrandSelected(it))
                },
                onDismiss = {
                    showBottomSheet = false
                },
            )
        }
    }
}

@Composable
private fun PasswordTextField(
    password: String,
    onNewCardScreenEvent: (RegisterCardScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = password,
        onValueChange = {
            onNewCardScreenEvent(RegisterCardScreenEvent.OnPasswordChanged(it))
        },
        label = { Text(stringResource(id = R.string.label_passwrod)) },
        placeholder = { Text(stringResource(id = R.string.placeholder_password)) },
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done,
            ),
        keyboardActions =
            KeyboardActions(
                onDone = { focusManager.clearFocus(true) },
            ),
        modifier = modifier,
    )
}

@Composable
private fun ExpiredDateTextField(
    expiredDate: String,
    expiredDateMonthValidResult: ExpiredDateMonthValidResult,
    onNewCardScreenEvent: (RegisterCardScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = expiredDate,
        onValueChange = {
            onNewCardScreenEvent(RegisterCardScreenEvent.OnExpiredDateChanged(it))
        },
        label = { Text(stringResource(id = R.string.label_expired_date)) },
        placeholder = { Text(stringResource(id = R.string.placeholder_expired_date)) },
        visualTransformation = ExpiredDateVisualTransformation(),
        isError = expiredDateMonthValidResult.isError(),
        supportingText = {
            if (expiredDateMonthValidResult.isError()) {
                Text(text = stringResource(id = R.string.error_expired_date_month_range))
            }
        },
        singleLine = true,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
        keyboardActions =
            KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) },
            ),
        modifier = modifier,
    )
}

@Composable
private fun CardNumberTextField(
    cardNumber: String,
    onNewCardScreenEvent: (RegisterCardScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = cardNumber,
        onValueChange = {
            onNewCardScreenEvent(RegisterCardScreenEvent.OnCardNumberChanged(it))
        },
        label = { Text(stringResource(id = R.string.label_card_number)) },
        placeholder = { Text(stringResource(id = R.string.placeholder_card_number)) },
        visualTransformation = CardNumberVisualTransformation(),
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
        keyboardActions =
            KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) },
            ),
        singleLine = true,
        modifier = modifier,
    )
}

@Composable
private fun OwnerNameTextField(
    ownerName: String,
    ownerNameValidResult: OwnerNameValidResult,
    onNewCardScreenEvent: (RegisterCardScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = ownerName,
        onValueChange = {
            onNewCardScreenEvent(RegisterCardScreenEvent.OnOwnerNameChanged(it))
        },
        label = { Text(stringResource(id = R.string.label_owner_name)) },
        placeholder = { Text(stringResource(id = R.string.placeholder_owner_name)) },
        supportingText = {
            if (ownerNameValidResult.isError()) {
                Text(text = stringResource(id = R.string.error_owner_name_length))
            }
        },
        isError = ownerNameValidResult.isError(),
        singleLine = true,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
        keyboardActions =
            KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) },
            ),
        modifier = modifier,
    )
}

@Preview
@Composable
private fun NewCardScreenPreview(
    @PreviewParameter(RegisterCardScreenProvider::class) uiState: RegisterCardUiState,
) {
    PaymentsTheme {
        RegisterCardScreen(
            uiState = uiState,
            navigateUp = {},
            onNewCardScreenEvent = {},
        )
    }
}

private class RegisterCardScreenProvider : PreviewParameterProvider<RegisterCardUiState> {
    override val values: Sequence<RegisterCardUiState> =
        sequenceOf(
            RegisterCardUiState.Register(
                brand = Brand.NONE,
                cardNumber = "1234567812345678",
                expiredDate = "1234",
                ownerName = "홍길동",
                password = "1234",
                ownerNameValidResult = OwnerNameValidResult.VALID,
                expiredDateMonthValidResult = ExpiredDateMonthValidResult.VALID,
                registerEnabled = true,
            ),
            RegisterCardUiState.Update(
                brand = Brand.NONE,
                cardNumber = "1234567812345678",
                expiredDate = "1234",
                ownerName =
                    "김수한무 거북이와 두루미 삼천갑자 동방삭 치치카포 사리사리센타 워리워리 세브리깡 무두셀라 구름이 허리케인에 담벼락 담벼락에 서생원 서생원에 고양이 고양이엔 바둑이 바둑이는 돌돌이",
                password = "1234",
                ownerNameValidResult = OwnerNameValidResult.ERROR_OWNER_NAME_LENGTH,
                expiredDateMonthValidResult = ExpiredDateMonthValidResult.VALID,
                registerEnabled = false,
            ),
            RegisterCardUiState.Update(
                brand = Brand.NONE,
                cardNumber = "1234567812345678",
                expiredDate = "3456",
                ownerName =
                    "김수한무 거북이와 두루미 삼천갑자 동방삭 치치카포 사리사리센타 워리워리 세브리깡 무두셀라 구름이 허리케인에 담벼락 담벼락에 서생원 서생원에 고양이 고양이엔 바둑이 바둑이는 돌돌이",
                password = "1234",
                ownerNameValidResult = OwnerNameValidResult.VALID,
                expiredDateMonthValidResult = ExpiredDateMonthValidResult.ERROR_EXPIRED_DATE_MONTH_RANGE,
                registerEnabled = false,
            ),
        )
}
