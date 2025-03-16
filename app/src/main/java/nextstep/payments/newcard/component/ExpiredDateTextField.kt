package nextstep.payments.newcard.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R
import nextstep.payments.newcard.model.Validation

private const val EXPIRED_DATE_PLACEHOLDER = "YY / MM"

@Composable
fun ExpiredDateTextField(
    expiredDate: String,
    validation: Validation,
    setExpiredDate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        value = expiredDate,
        onValueChange = {
            if (it.length <= 4) {
                setExpiredDate(it)
            }
        },
        isError = validation !is Validation.Success,
        supportingText = {
            if (validation is Validation.Error) {
                Text(stringResource(validation.msgId))
            }
        },
        label = { Text(stringResource(R.string.expired_date)) },
        placeholder = { Text(text = EXPIRED_DATE_PLACEHOLDER) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),

        visualTransformation = ExpiredDateVisualTransformation(),
    )
}

private class ExpiredDateVisualTransformation : VisualTransformation {
    private class ExpiredDateOffsetMapping : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return if (offset <= DATE_LENGTH) {
                offset
            } else {
                offset + SEPARATOR.length
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when {
                offset <= DATE_LENGTH -> offset
                offset < DATE_LENGTH + SEPARATOR.length -> DATE_LENGTH
                else -> offset - SEPARATOR.length
            }
        }
    }

    override fun filter(text: AnnotatedString): TransformedText {
        val groups = text.text.chunked(DATE_LENGTH)
        val result = groups.joinToString(separator = SEPARATOR)
        return TransformedText(AnnotatedString(result), ExpiredDateOffsetMapping())
    }

    companion object {
        private const val DATE_LENGTH = 2  // YY, MM
        private const val SEPARATOR = " / "
    }
}


@Preview(showBackground = true)
@Composable
private fun ExpiredDateTextFieldPreview() {
    val expiredDate = remember { mutableStateOf("") }

    ExpiredDateTextField(
        expiredDate = expiredDate.value,
        validation = Validation.Success,
        setExpiredDate = { expiredDate.value = it }
    )
}
