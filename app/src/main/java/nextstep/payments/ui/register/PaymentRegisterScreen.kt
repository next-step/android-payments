package nextstep.payments.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun PaymentRegisterScreen() {
    var cardNumber: String by remember { mutableStateOf("") }
    var expiredDate: String by remember { mutableStateOf("") }
    var ownerName: String by remember { mutableStateOf("") }
    var cvc: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }

    PaymentRegisterScreen(
        cardNumber = cardNumber,
        onCardNumberChange = { cardNumber = it },
        expiredDate = expiredDate,
        onExpiredDateChange = { expiredDate = it },
        ownerName = ownerName,
        onOwnerNameChange = { ownerName = it },
        cvc = cvc,
        onCvcChange = { cvc = it },
        password = password,
        onPasswordChange = { password = it },
    )
}

@Composable
internal fun PaymentRegisterScreen(
    cardNumber: String,
    onCardNumberChange: (String) -> Unit,
    expiredDate: String,
    onExpiredDateChange: (String) -> Unit,
    ownerName: String,
    onOwnerNameChange: (String) -> Unit,
    cvc: String,
    onCvcChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            PaymentRegisterTopAppBar(
                onBackClick = {},
                onCheckClick = {},
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            CardImage(
                painterResource(id = R.drawable.img_card),
                modifier = Modifier
                    .padding(16.dp)
                    .width(208.dp)
                    .align(Alignment.CenterHorizontally),
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                PaymentTextField(
                    value = cardNumber,
                    onValueChange = onCardNumberChange,
                    label = stringResource(id = R.string.payment_card_number_label),
                    placeholder = stringResource(id = R.string.payment_card_number_placeholder),
                    modifier = Modifier.fillMaxWidth(),
                )
                PaymentTextField(
                    value = expiredDate,
                    onValueChange = onExpiredDateChange,
                    label = stringResource(id = R.string.payment_card_expired_date_label),
                    placeholder = stringResource(id = R.string.payment_card_expired_date_placeholder),
                    modifier = Modifier.fillMaxWidth(),
                )
                PaymentTextField(
                    value = ownerName,
                    onValueChange = {
                        onOwnerNameChange(it.take(OWNER_NAME_MAX_LENGGTH))
                    },
                    label = stringResource(id = R.string.payment_card_owner_name_label),
                    placeholder = stringResource(id = R.string.payment_card_owner_name_placeholder),
                    modifier = Modifier.fillMaxWidth(),
                    supportingText = {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "${ownerName.length} / $OWNER_NAME_MAX_LENGGTH",
                                modifier = Modifier.align(Alignment.CenterEnd),
                                style = MaterialTheme.typography.labelSmall,
                            )
                        }
                    }
                )
                PaymentTextField(
                    value = cvc,
                    onValueChange = onCvcChange,
                    label = stringResource(id = R.string.payment_card_cvc_label),
                    placeholder = stringResource(id = R.string.payment_card_cvc_placeholder),
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                )
                PaymentTextField(
                    value = password,
                    onValueChange = onPasswordChange,
                    label = stringResource(id = R.string.payment_card_password_label),
                    placeholder = stringResource(id = R.string.payment_card_password_placeholder),
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PaymentRegisterTopAppBar(
    onBackClick: () -> Unit,
    onCheckClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.payment_register_title))
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = onCheckClick) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null)
            }
        },
    )
}

@Composable
private fun CardImage(
    painter: Painter,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painter,
        contentDescription = "카드 이미지",
        modifier = modifier,
        contentScale = ContentScale.FillWidth,
    )
}

@Composable
private fun PaymentTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    supportingText: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            if (value.isBlank()) {
                Text(text = placeholder, color = Color(0xFFAAAAAA))
            } else {
                Text(text = label)
            }
        },
        supportingText = supportingText,
        singleLine = true,
        visualTransformation = visualTransformation,
    )
}

private const val OWNER_NAME_MAX_LENGGTH = 30

@Preview
@Composable
private fun PaymentRegisterPreview() {
    PaymentsTheme {
        PaymentRegisterScreen(
            cardNumber = "",
            onCardNumberChange = {},
            expiredDate = "",
            onExpiredDateChange = {},
            ownerName = "",
            onOwnerNameChange = {},
            cvc = "",
            onCvcChange = {},
            password = "",
            onPasswordChange = {},
        )
    }
}
