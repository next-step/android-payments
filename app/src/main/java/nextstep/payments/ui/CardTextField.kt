package nextstep.payments.ui

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CardTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelResId: Int,
    placeholderResId: Int,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(id = labelResId)) },
        placeholder = { Text(stringResource(id = placeholderResId)) },
        modifier = modifier,
        visualTransformation = visualTransformation
    )
}
