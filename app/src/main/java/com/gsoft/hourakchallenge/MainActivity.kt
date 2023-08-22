package com.gsoft.hourakchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gsoft.hourakchallenge.presentation.detailScreen.DetailScreen
import com.gsoft.hourakchallenge.presentation.searchScreen.SearchScreen
import com.gsoft.hourakchallenge.presentation.splashScreen.SplashScreen
import com.gsoft.hourakchallenge.ui.theme.HourakChallengeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            HourakChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "splash") {
                        composable("splash") { SplashScreen(navController = navController) }
                        composable("search") { SearchScreen(navController) }
                        composable("detail") { DetailScreen(navController) }
                    }
                }
            }
        }
    }
}



