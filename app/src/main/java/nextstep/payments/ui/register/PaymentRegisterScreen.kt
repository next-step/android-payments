package nextstep.payments.ui.register

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            // 카드 번호

            // 만료일

            // 카드 소유자 이름

            // 보안코드

            // 비밀번호
        }
    }
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
