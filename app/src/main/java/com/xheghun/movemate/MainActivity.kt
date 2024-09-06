package com.xheghun.movemate

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xheghun.movemate.presentation.screens.CalculateScreen
import com.xheghun.movemate.presentation.screens.HomeScreen
import com.xheghun.movemate.presentation.screens.SearchScreen
import com.xheghun.movemate.presentation.screens.ShipmentHistoryScreen
import com.xheghun.movemate.presentation.screens.TotalScreen
import com.xheghun.movemate.presentation.ui.Routes
import com.xheghun.movemate.presentation.ui.theme.MovemateTheme
import com.xheghun.movemate.presentation.ui.theme.colorStatusBar
import com.xheghun.movemate.presentation.ui.updateStatusBarColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MovemateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Home.name,
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        composable(route = Routes.Home.name) {
                            updateStatusBarColor(colorStatusBar)
                            HomeScreen(navController)
                        }

                        composable(route = Routes.Search.name) {
                            updateStatusBarColor(colorStatusBar)
                            SearchScreen(navController)
                        }

                        composable(route = Routes.ShipmentHistory.name) {
                            updateStatusBarColor(colorStatusBar)
                            ShipmentHistoryScreen(navController)
                        }

                        composable(route = Routes.Calculate.name) {
                            updateStatusBarColor(colorStatusBar)
                            CalculateScreen(navController)
                        }

                        composable(route = Routes.Total.name) {
                            updateStatusBarColor(Color.WHITE)
                            TotalScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovemateTheme {
        Greeting("Android")
    }
}