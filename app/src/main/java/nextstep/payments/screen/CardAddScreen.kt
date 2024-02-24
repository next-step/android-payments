package nextstep.payments.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

            }
        }
    )
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
