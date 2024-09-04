package com.xheghun.movemate

import android.os.Bundle
import androidx.activity.ComponentActivity
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
                        startDestination = Routes.Total.name,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = Routes.Home.name) {
                            HomeScreen(navController)
                        }

                        composable(route = Routes.Search.name) {
                            SearchScreen(navController)
                        }

                        composable(route = Routes.ShipmentHistory.name) {
                            ShipmentHistoryScreen(navController)
                        }

                        composable(route = Routes.Calculate.name) {
                            CalculateScreen(navController)
                        }

                        composable(route = Routes.Total.name) {
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