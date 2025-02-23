package nextstep.payments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import nextstep.payments.ui.add.CardAddScreen
import nextstep.payments.ui.theme.PaymentsTheme

class CardAddActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CardAddScreen()
                }
            }
        }
    }

    companion object {
        fun open(activity: Activity) {
            val intent = Intent(activity, CardAddActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
