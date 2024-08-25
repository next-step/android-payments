package nextstep.payments.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R

@Composable
fun AddPaymentCard(
    onAddPaymentCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onAddPaymentCard,
        modifier = modifier
            .size(width = 208.dp, height = 124.dp)
            .background(Color.LightGray, RoundedCornerShape(5.dp))
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.add_new_card_content_description)
        )
    }
}

@Preview
@Composable
private fun AddPaymentCardPreview() {
    AddPaymentCard(
        onAddPaymentCard = { }
    )
}