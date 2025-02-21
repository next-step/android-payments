package nextstep.payments.ui.newcard.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.PasswordVisualTransformation
import nextstep.payments.R


@Composable
fun CardPasswordTextFiled(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.text_filed_label_password)) },
        placeholder = { Text(stringResource(R.string.text_filed_placeholder_password)) },
        modifier = modifier
            .fillMaxWidth()
            .semantics { contentDescription = "CardPasswordTextFiled" },
        visualTransformation = PasswordVisualTransformation(),
    )
}