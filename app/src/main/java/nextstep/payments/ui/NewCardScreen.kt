package nextstep.payments.ui

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.R
import nextstep.payments.model.CardCompanyType
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.viewmodel.NewCardViewModel

@Composable
internal fun NewCardScreen(
    viewModel: NewCardViewModel,
    navigateToCardList: () -> Unit,
    onBackButtonClicked: () -> Unit,
    onSaveButtonClicked: () -> Unit,
) {
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val cardCompanyState by viewModel.cardCompanyType.collectAsStateWithLifecycle()

    val cardAdded by viewModel.cardAdded.collectAsStateWithLifecycle()

    LaunchedEffect(cardAdded) {
        if (cardAdded) navigateToCardList()
    }

    NewCardScreenContent(
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        onBackButtonClicked = onBackButtonClicked,
        onSaveButtonClicked = onSaveButtonClicked,
        cardCompanyState = cardCompanyState,
    )
}

@Composable
private fun NewCardScreenContent(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onBackButtonClicked: () -> Unit,
    onSaveButtonClicked: () -> Unit,
    cardCompanyState: CardCompanyType,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CardTopBar(
                onBackClick = onBackButtonClicked,
                onSaveClick = onSaveButtonClicked,
                title = stringResource(id = R.string.new_card_screen_top_bar_title)
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

            cardCompanyState.Render()

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = cardNumber,
                onValueChange = { setCardNumber(it) },
                label = { Text(stringResource(id = R.string.new_card_screen_card_number_label)) },
                placeholder = { Text(stringResource(id = R.string.new_card_screen_card_number_hint)) },
                modifier = Modifier.fillMaxWidth(),
            )

            CardTextField(
                value = expiredDate,
                onValueChange = setExpiredDate,
                labelResId = R.string.new_card_screen_card_expired_label,
                placeholderResId = R.string.new_card_screen_card_expired_hint,
                modifier = Modifier.fillMaxWidth()
            )

            CardTextField(
                value = ownerName,
                onValueChange = setOwnerName,
                labelResId = R.string.new_card_screen_card_owner_name_label,
                placeholderResId = R.string.new_card_screen_card_owner_name_hint,
                modifier = Modifier.fillMaxWidth()
            )

            CardTextField(
                value = password,
                onValueChange = setPassword,
                labelResId = R.string.new_card_screen_card_password_label,
                placeholderResId = R.string.new_card_screen_card_password_hint,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
        }
    }
}

@Preview
@Composable
fun NewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            viewModel = NewCardViewModel().apply {
                setCardNumber("0000 - 0000 - 0000 - 0000")
                setExpiredDate("00 / 00")
                setOwnerName("최고심")
                setPassword("1234")
            },
            navigateToCardList = {},
            onBackButtonClicked = {},
            onSaveButtonClicked = {},
        )
    }
}

@Preview
@Composable
private fun NewCardScreenContentPreview() {
    PaymentsTheme {
        NewCardScreenContent(
            cardNumber = "0000 - 0000 - 0000 - 0000",
            expiredDate = "00 / 00",
            ownerName = "최고심",
            password = "1234",
            setCardNumber = {},
            setExpiredDate = {},
            setOwnerName = {},
            setPassword = {},
            onBackButtonClicked = {},
            onSaveButtonClicked = {},
            cardCompanyState = CardCompanyType.Bc("BC카드")
        )
    }
}
