package nextstep.payments.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.model.Brand
import nextstep.payments.model.toIcon
import nextstep.payments.model.toName
import nextstep.payments.ui.theme.PaymentsTheme

private const val COLUMN_COUNT = 4
private const val ROW_COUNT = 2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    onBrandSelected: (Brand) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    brands: List<Brand> = Brand.entries.drop(1),
    selectedBrand: Brand = Brand.NONE,
    modalBottomSheetState: SheetState =
        rememberModalBottomSheetState(
            confirmValueChange = { false },
        ),
) {
    LaunchedEffect(key1 = selectedBrand) {
        if (selectedBrand != Brand.NONE) {
            modalBottomSheetState.hide()
            onDismiss()
        }
    }

    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = onDismiss,
        modifier = modifier,
    ) {
        BankSelectRow(
            brands = brands,
            onClick = onBrandSelected,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(300.dp),
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BankSelectRow(
    brands: List<Brand>,
    onClick: (Brand) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier.padding(vertical = 36.dp),
        verticalArrangement = Arrangement.spacedBy(23.dp),
        horizontalArrangement = Arrangement.Center,
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        repeat(ROW_COUNT * COLUMN_COUNT) {
            val brand = brands[it]
            BankCard(
                brand = brand,
                modifier =
                    Modifier
                        .size(80.dp)
                        .clickable { onClick(brand) },
            )
        }
    }
}

@Composable
private fun BankCard(
    brand: Brand,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Image(
            painter = brand.toIcon(),
            contentDescription = stringResource(id = R.string.brand_icon_content_description),
        )
        Spacer(modifier = Modifier.height(9.dp))
        Text(
            text = brand.toName(),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BankSelectRowPreview() {
    PaymentsTheme {
        BankSelectRow(
            brands = Brand.entries.drop(1),
            onClick = {},
        )
    }
}
