package nextstep.payments.ui.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.component.CreditCardVisualTransformation
import nextstep.payments.ui.component.ExpirationDateVisualTransformation
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.component.PaymentInputField

@Composable
fun AddCardScreen(
    onBackClick: () -> Unit,
    onAddCardClick: () -> Unit,
) {
    var cardNumber: String by remember { mutableStateOf("") }
    var expirationDate: String by remember { mutableStateOf("") }
    var ownerName: String by remember { mutableStateOf("") }
    var cvcNumber: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            AddCardAppbar(
                onBackClick = onBackClick,
                onAddCardClick = onAddCardClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .padding(15.dp)
                .verticalScroll(state = rememberScrollState())
        ) {
            PaymentCard(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 12.dp)
            )
            PaymentInputField(
                modifier = Modifier.fillMaxWidth(),
                text = cardNumber,
                onTextChange = { cardNumber = it },
                label = stringResource(id = R.string.card_number_label),
                hint = stringResource(id = R.string.card_number_hint),
                visualTransformation = CreditCardVisualTransformation()
            )

            PaymentInputField(
                text = expirationDate,
                onTextChange = { expirationDate = it },
                label = stringResource(id = R.string.card_expiration_date_label),
                hint = stringResource(id = R.string.card_expiration_date_hint),
                visualTransformation = ExpirationDateVisualTransformation()
            )
            PaymentInputField(
                modifier = Modifier.fillMaxWidth(),
                text = ownerName,
                onTextChange = { ownerName = it },
                label = stringResource(id = R.string.card_owner_name_label),
                hint = stringResource(id = R.string.card_owner_name_hint)
            )
            PaymentInputField(
                text = cvcNumber,
                onTextChange = { cvcNumber = it },
                label = stringResource(id = R.string.card_cvc_code_label),
                hint = stringResource(id = R.string.card_cvc_code_hint),
                visualTransformation = PasswordVisualTransformation()
            )
            PaymentInputField(
                text = password,
                onTextChange = { password = it },
                label = stringResource(id = R.string.card_password_label),
                hint = stringResource(id = R.string.card_password_hint),
                visualTransformation = PasswordVisualTransformation()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardAppbar(
    onBackClick: () -> Unit,
    onAddCardClick: () -> Unit
) {
    TopAppBar(title = { Text(text = stringResource(id = R.string.add_card_title)) },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.app_on_back_click)
                )
            }
        },
        actions = {
            IconButton(onClick = { onAddCardClick() }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(id = R.string.add_card_title)
                )
            }
        })

}

@Preview
@Composable
fun AddCardScreenPreview() {
    AddCardScreen(onBackClick = {}, onAddCardClick = {})
}
