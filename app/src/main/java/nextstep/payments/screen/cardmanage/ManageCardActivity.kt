package nextstep.payments.screen.cardmanage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.payments.ui.theme.PaymentsTheme

class ManageCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                ManageCardRouteScreen(
                    navigateToCardList = { isChanged ->
                        if(isChanged == ManageCardEvent.Success) setResult(RESULT_OK)
                        finish()
                    }
                )
            }
        }
    }
}
