package nextstep.payments.screen

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R

@Composable
fun CardAddScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CardTopBar()
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues = paddingValues)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(state = rememberScrollState())
            ) {
                CardImage(modifier = Modifier.padding(top = 14.dp))
                var cardNumber by remember { mutableStateOf("") }
                CardNumberTextField(
                    cardNumber = cardNumber,
                    onCardNumberChange = { value ->
                        cardNumber = value
                    }
                )
                var expirationDate by remember { mutableStateOf("") }
                ExpirationDateTextField(
                    expirationDate = expirationDate,
                    onExpirationDateChange = { value ->
                        expirationDate = value
                    }
                )
                var owner by remember { mutableStateOf("") }
                CardOwnerTextField(
                    owner = owner,
                    onOwnerChange = { value ->
                        owner = value
                    }
                )
                var cvc by remember { mutableStateOf("") }
                CVCTextField(
                    cvc = cvc,
                    onCVCChange = { value ->
                        cvc = value
                    }
                )
                var password by remember { mutableStateOf("") }
                PasswordTextField(
                    password = password,
                    onPasswordChange = { value ->
                        password = value
                    }
                )
            }
        }
    )
}

@Composable
private fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        value = password,
        onValueChange = { value ->
            onPasswordChange(value)
        },
        label = {
            Text(text = stringResource(id = R.string.password))
        },
        placeholder = {
            Text(text = stringResource(id = R.string.hint_password))
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.NumberPassword
        ),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
private fun CVCTextField(
    cvc: String,
    onCVCChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        value = cvc,
        onValueChange = { value ->
            onCVCChange(value)
        },
        label = {
            Text(text = stringResource(id = R.string.cvc))
        },
        placeholder = {
            Text(text = stringResource(id = R.string.hint_password))
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.NumberPassword,
        ),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
private fun CardOwnerTextField(
    owner: String,
    onOwnerChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        value = owner,
        onValueChange = { value ->
            onOwnerChange(value)
        },
        label = {
            Text(text = stringResource(id = R.string.card_owner))
        },
        placeholder = {
            Text(text = stringResource(id = R.string.hint_card_owner))
        }
    )
}

@Composable
private fun ExpirationDateTextField(
    expirationDate: String,
    onExpirationDateChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        value = expirationDate,
        onValueChange = { value ->
            onExpirationDateChange(value)
        },
        label = {
            Text(text = stringResource(id = R.string.expiration_date))
        },
        placeholder = {
            Text(text = stringResource(id = R.string.hint_expiration_date))
        }
    )
}

@Composable
private fun CardNumberTextField(
    cardNumber: String,
    onCardNumberChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        value = cardNumber,
        onValueChange = { value ->
            onCardNumberChange(value)
        },
        label = {
            Text(text = stringResource(id = R.string.card_number))
        },
        placeholder = {
            Text(text = stringResource(id = R.string.hint_card_number))
        }
    )
}

@Composable
private fun CardImage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(width = 208.dp)
                .height(height = 124.dp)
                .drawBehind {
                    drawIntoCanvas { canvas ->
                        val paint = Paint()
                        with(paint.asFrameworkPaint()) {
                            color = Color.Black
                                .copy(alpha = 0.25f)
                                .toArgb()
                            maskFilter = BlurMaskFilter(
                                5.dp.toPx(),
                                BlurMaskFilter.Blur.NORMAL
                            )
                        }
                        canvas.drawRoundRect(
                            left = 3.dp.toPx(),
                            top = 3.dp.toPx(),
                            right = size.width,
                            bottom = size.height,
                            radiusX = 4.dp.toPx(),
                            radiusY = 4.dp.toPx(),
                            paint = paint
                        )
                    }
                }
                .clip(shape = RoundedCornerShape(size = 4.dp))
                .background(color = Color(0xff333333))
                .padding(horizontal = 14.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .padding(top = 44.dp)
                    .width(width = 40.dp)
                    .height(height = 26.dp)
                    .clip(shape = RoundedCornerShape(size = 4.dp))
                    .background(color = Color(0xFFCBBA64))
            )
        }
    }
}

@Composable
private fun CardTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 64.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .padding(start = 4.dp)
                .size(size = 48.dp)
                .padding(all = 12.dp),
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.go_back)
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .weight(weight = 1f),
            text = stringResource(id = R.string.add_card),
            fontSize = 22.sp
        )
        Image(
            modifier = Modifier
                .padding(end = 4.dp)
                .size(size = 48.dp)
                .padding(all = 12.dp),
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.add)
        )
    }
}

@Preview
@Composable
private fun CardAddScreenPreview() {
    CardAddScreen()
}
