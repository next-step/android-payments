package nextstep.payments.ui.editcard

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.viewModels
import nextstep.payments.model.card.CreditCard
import nextstep.payments.ui.theme.PaymentsTheme


internal class EditCardActivity : ComponentActivity() {
    private val viewModel: EditCardViewModel by viewModels { EditCardViewModel.Factory }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PaymentsTheme {
                EditCardScreen(
                    viewModel = viewModel,
                    onCardUpdated = {
                        setResult(RESULT_OK)
                        finish()
                    },
                    onBackClick = this::finish
                )
            }
        }
    }

    companion object {
        const val EXTRA_KEY = "edit_card"

        fun startActivity(
            targetCard: CreditCard,
            context: Context,
            launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
        ) {
            val intent = Intent(context, EditCardActivity::class.java)
            intent.putExtra(EXTRA_KEY, targetCard)
            launcher.launch(intent)
        }
    }
}