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
import nextstep.payments.common.model.Validation

private const val PASSWORD_PLACEHOLDER = "0000"

@Composable
fun PasswordTextField(
    password: String,
    validation: Validation,
    setPassword: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        value = password,
        onValueChange = {
            if (it.length <= 4) {
                setPassword(it)
            }
        },
        isError = validation is Validation.Failure.Error,
        supportingText = {
            if (validation is Validation.Failure.Error) {
                Text(stringResource(validation.msgId))
            }
        },
        label = { Text(stringResource(R.string.password)) },
        placeholder = { Text(PASSWORD_PLACEHOLDER) },
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
        validation = Validation.Success,
        setPassword = { password.value = it }
    )
}

