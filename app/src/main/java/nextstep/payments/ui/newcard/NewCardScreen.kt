package nextstep.payments.ui.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun NewCardRoute(
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NewCardScreen(
        uiState = uiState,
        onCardNumberChange = viewModel::setCardNumber,
        onExpiredDateChange = viewModel::setExpiredDate,
        onOwnerNameChange = viewModel::setOwnerName,
        onPasswordChange = viewModel::setPassword,
        modifier = modifier,
    )
}

@Composable
internal fun NewCardScreen(
    uiState: NewCardUiState,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = { TODO() },
                onSaveClick = { TODO() },
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
                onValueChange = onCardNumberChange,
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
                onValueChange = onExpiredDateChange,
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
                onValueChange = onOwnerNameChange,
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
                onValueChange = onPasswordChange,
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
    @PreviewParameter(NewCardScreenProvider::class) uiState: NewCardUiState,
) {
    PaymentsTheme {
        NewCardScreen(
            uiState = uiState,
            onCardNumberChange = {},
            onExpiredDateChange = {},
            onOwnerNameChange = {},
            onPasswordChange = {},
        )
    }
}

private class NewCardScreenProvider : PreviewParameterProvider<NewCardUiState> {
    override val values: Sequence<NewCardUiState> =
        sequenceOf(
            NewCardUiState(
                brand = Brand.NONE,
                cardNumber = "1234567812345678",
                expiredDate = "1234",
                ownerName = "홍길동",
                password = "1234",
                ownerNameValidResult = OwnerNameValidResult.VALID,
            ),
            NewCardUiState(
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
