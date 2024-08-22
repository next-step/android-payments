package nextstep.payments.ui.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.model.Brand
import nextstep.payments.model.OwnerNameValidResult
import nextstep.payments.ui.component.CardNumberVisualTransformation
import nextstep.payments.ui.component.ExpiredDateVisualTransformation
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.component.PaymentsTopBar
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun RegisterCardRoute(
    navigateUp: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterCardViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect {
            when (it) {
                is RegisterCardScreenEffect.NavigateToCardListScreen -> navigateUp(it.shouldFetchCards)
            }
        }
    }

    RegisterCardScreen(
        uiState = uiState,
        navigateUp = navigateUp,
        onNewCardScreenEvent = viewModel::dispatchEvent,
        modifier = modifier,
    )
}

@Composable
internal fun RegisterCardScreen(
    uiState: RegisterCardUiState,
    navigateUp: (Boolean) -> Unit,
    onNewCardScreenEvent: (RegisterCardScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            PaymentsTopBar(
                title = stringResource(id = R.string.title_new_card),
                onBackClick = { navigateUp(false) },
                actions = {
                    IconButton(onClick = { onNewCardScreenEvent(RegisterCardScreenEvent.OnRegisterCardClicked) }) {
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
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp),
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            PaymentCard()

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = uiState.cardNumber,
                onValueChange = {
                    onNewCardScreenEvent(RegisterCardScreenEvent.OnCardNumberChanged(it))
                },
                label = { Text(stringResource(id = R.string.label_card_number)) },
                placeholder = { Text(stringResource(id = R.string.placeholder_card_number)) },
                visualTransformation = CardNumberVisualTransformation(),
                singleLine = true,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag("cardNumber"),
            )

            OutlinedTextField(
                value = uiState.expiredDate,
                onValueChange = {
                    onNewCardScreenEvent(RegisterCardScreenEvent.OnExpiredDateChanged(it))
                },
                label = { Text(stringResource(id = R.string.label_expired_date)) },
                placeholder = { Text(stringResource(id = R.string.placeholder_expired_date)) },
                visualTransformation = ExpiredDateVisualTransformation(),
                singleLine = true,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag("expiredDate"),
            )

            OutlinedTextField(
                value = uiState.ownerName,
                onValueChange = {
                    onNewCardScreenEvent(RegisterCardScreenEvent.OnOwnerNameChanged(it))
                },
                label = { Text(stringResource(id = R.string.label_owner_name)) },
                placeholder = { Text(stringResource(id = R.string.placeholder_owner_name)) },
                supportingText = {
                    if (uiState.ownerNameValidResult.isError()) {
                        Text(text = stringResource(id = R.string.error_owner_name_length))
                    }
                },
                isError = uiState.ownerNameValidResult.isError(),
                singleLine = true,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag("ownerName"),
            )

            OutlinedTextField(
                value = uiState.password,
                onValueChange = {
                    onNewCardScreenEvent(RegisterCardScreenEvent.OnPasswordChanged(it))
                },
                label = { Text(stringResource(id = R.string.label_passwrod)) },
                placeholder = { Text(stringResource(id = R.string.placeholder_password)) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag("password"),
            )
        }
    }
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
            RegisterCardUiState(
                brand = Brand.NONE,
                cardNumber = "1234567812345678",
                expiredDate = "1234",
                ownerName = "홍길동",
                password = "1234",
                ownerNameValidResult = OwnerNameValidResult.VALID,
            ),
            RegisterCardUiState(
                brand = Brand.NONE,
                cardNumber = "1234567812345678",
                expiredDate = "1234",
                ownerName =
                    "김수한무 거북이와 두루미 삼천갑자 동방삭 치치카포 사리사리센타 워리워리 세브리깡 무두셀라 구름이 허리케인에 담벼락 담벼락에 서생원 서생원에 고양이 고양이엔 바둑이 바둑이는 돌돌이",
                password = "1234",
                ownerNameValidResult = OwnerNameValidResult.ERROR_OWNER_NAME_LENGTH,
            ),
        )
}
