package nextstep.payments.ui.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun PaymentRegisterScreen() {
    Scaffold(
        topBar = {
            PaymentRegisterTopAppBar(
                onBackClick = {},
                onCheckClick = {},
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {

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

@Preview
@Composable
private fun PaymentRegisterPreview() {
    PaymentsTheme {
        PaymentRegisterScreen()
    }
}
