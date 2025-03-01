package nextstep.payments.newcard.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R

@Composable
fun PasswordTextField(
    password: String,
    setPassword: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val placeholder = remember { "0000" }

    OutlinedTextField(
        modifier = modifier,
        value = password,
        onValueChange = {
            if (it.length <= 4) {
                setPassword(it)
            }
        },
        label = { Text(stringResource(R.string.password)) },
        placeholder = { Text(placeholder) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        visualTransformation = PasswordVisualTransformation(),
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreview() {
    val password = remember { mutableStateOf("") }

    PasswordTextField(
        password = password.value,
        setPassword = { password.value = it }
    )
}

