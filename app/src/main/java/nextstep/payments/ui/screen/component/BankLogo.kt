package nextstep.payments.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R

@Composable
fun BankLogo(
    bankImage: Painter,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = bankImage,
        contentDescription = "Bank Logo",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun BankLogoPreview() {
    BankLogo(
        bankImage = painterResource(R.drawable.bc),
    )
}
