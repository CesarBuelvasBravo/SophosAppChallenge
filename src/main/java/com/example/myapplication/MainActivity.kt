package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.myapplication.R
import com.example.myapplication.view.ui.SophosActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeSophosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //NAVIGATION
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.ScreenLogin.route
                    ) {

                        composable(Routes.ScreenLogin.route) {
                            ScreenLogin(
                                loginViewModel = LoginViewModel(),
                                navigationController
                            )
                        }

                        composable(
                            Routes.ScreenMain.route,
                            arguments = listOf(navArgument("name") {
                                type = NavType.StringType

                            })
                        ) { backStackEntry ->
                            ScreenMain(
                                navigationController,
                                backStackEntry.arguments?.getString("name")!!
                            )
                        }

                        composable(Routes.ScreenOfficeMap.route) {
                            ScreenOfficeMap(

                            )
                        }
                        composable(Routes.ScreenSendDocument.route){
                            ScreenSendDocuments(
                                navigationController
                            )
                        }

                        composable(Routes.ScreenConsultDocuments.route){
                            ScreenConsultDocuments(
                                navigationController
                            )
                        }
                    }
                }
            }
        }
    }
}
