package nextstep.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nextstep.payments.model.PaymentsRoute
import nextstep.payments.screen.CardListScreen
import nextstep.payments.screen.NewCardScreen
import nextstep.payments.ui.theme.PaymentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                Scaffold(
                    modifier = Modifier
                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = PaymentsRoute.List,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<PaymentsRoute.List> {
                            CardListScreen()
                        }
                        composable<PaymentsRoute.Add> {
                            NewCardScreen(
                                onBack = navController::popBackStack
                            )
                        }
                    }
                }
            }
        }
    }
}
