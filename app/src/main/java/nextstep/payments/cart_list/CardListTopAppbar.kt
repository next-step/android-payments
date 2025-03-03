package nextstep.payments.cart_list

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopAppbar(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
    showAddTextButton: Boolean = false,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Payments") },
        modifier = modifier,
        actions = {
            if (showAddTextButton) {
                TextButton(
                    onClick = onAddClick,
                ) {
                    Text(
                        text = "추가",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700,
                        lineHeight = 36.sp,
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
        ),
    )
}

@Preview
@Composable
private fun CardListTopAppbarShowAddTextButtonPreview() {
    CardListTopAppbar(
        onAddClick = {},
        showAddTextButton = true,
    )
}

@Preview
@Composable
private fun CardListTopAppbarPreview() {
    CardListTopAppbar(
        onAddClick = {},
        showAddTextButton = false,
    )
}