package nextstep.payments.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import nextstep.payments.ui.theme.PaymentsTheme

abstract class BaseActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                Screen()
            }
        }
    }

    @Composable
    abstract fun Screen()
}
