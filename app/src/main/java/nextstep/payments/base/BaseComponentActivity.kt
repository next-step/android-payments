package nextstep.payments.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import nextstep.payments.designsystem.theme.PaymentsTheme

abstract class BaseComponentActivity : ComponentActivity() {

    @Composable
    abstract fun SetContent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                SetContent()
            }
        }
    }
}