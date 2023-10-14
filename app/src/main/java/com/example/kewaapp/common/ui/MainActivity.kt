package com.example.kewaapp.common.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kewaapp.common.domain.entities.User
import com.example.kewaapp.auth.presentation.screens.auth.AuthScreen
import com.example.kewaapp.auth.presentation.screens.LandingScreen
import com.example.kewaapp.auth.presentation.screens.auth.LoginSection
import com.example.kewaapp.common.NavConstants
import com.example.kewaapp.common.ui.theme.KewaAppTheme
import com.example.kewaapp.home.presentaion.screens.QuizResultScreen
import com.example.kewaapp.home.presentaion.screens.QuizScreen
import com.example.kewaapp.home.presentaion.screens.QuizScreenStarter
import com.example.kewaapp.home.presentaion.screens.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject





@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KewaAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = NavConstants.routeHome) {
                    composable("about") {

                    }
                    navigation(
                        startDestination = "login",
                        route = NavConstants.routeAuth
                    ) {
                        composable("login") {
                            AuthScreen(navController = navController)

                        }
                        /*composable("register") {
//                            val viewModel = it.sharedViewModel<SampleViewModel>(navController)
                        }
                        composable("forgot_password") {
//                            val viewModel = it.sharedViewModel<SampleViewModel>(navController)


                        }*/
                    }


                    navigation(
                        startDestination = NavConstants.HomeRoutes.routeQuizScreenStarter,
                        route = NavConstants.routeHome
                    ) {



                        composable("QuizScreenStarter") {
                            QuizScreenStarter(navController)
                        }
                        composable(
                            "${NavConstants.HomeRoutes.routeQuizScreen}/{quizId}",
                            arguments = listOf(navArgument("quizId") { type = NavType.StringType })

                        ) {backStackEntry ->

                            val viewModel = backStackEntry.sharedViewModel<QuizViewModel>(navController)


                            backStackEntry.arguments?.getString("quizId")?.let {
                                QuizScreen(
                                    navController=navController,
                                    quizId = it,
                                    viewModel=viewModel
                                )
                            }
                        }
                        composable(NavConstants.HomeRoutes.routeQuizResultScreen) {
                            val viewModel = it.sharedViewModel<QuizViewModel>(navController)

                            QuizResultScreen(viewModel)
                        }
                    }
                }
            }
        }
    }
}





@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}





/*


@Composable
private fun MainScreen() {
    Surface(color = MaterialTheme.colorScheme.background) {
        var showLandingScreen by remember {
            mutableStateOf(true)
        }
        if (showLandingScreen) {
            LandingScreen(onTimeout = { showLandingScreen = false })
        } else {
            AuthScreen()
        }
    }
}
*/

