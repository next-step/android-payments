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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            }
        }
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
            Text(text = "카드 소유자 이름 (선택)")
        },
        placeholder = {
            Text(text = "카드에 표시된 이름을 입력하세요.")
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
            Text(text = "만료일")
        },
        placeholder = {
            Text(text = "MM / YY")
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
            Text(text = "카드 번호")
        },
        placeholder = {
            Text(text = "0000 - 0000 - 0000 - 0000")
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
            contentDescription = "뒤로가기"
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .weight(weight = 1f),
            text = "카드 추가",
            fontSize = 22.sp
        )
        Image(
            modifier = Modifier
                .padding(end = 4.dp)
                .size(size = 48.dp)
                .padding(all = 12.dp),
            imageVector = Icons.Filled.Check,
            contentDescription = "추가"
        )
    }
}

@Preview
@Composable
private fun CardAddScreenPreview() {
    CardAddScreen()
}
