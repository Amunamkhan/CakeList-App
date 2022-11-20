package com.droident.cakelistapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.droident.cakelistapp.presentation.cake_listings.CakeListingsScreen
import com.droident.cakelistapp.presentation.ui.theme.CakeListAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CakeListAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    //Todo need to remove this it added for navigation
                    val navController = rememberNavController()
                    NavHost(navController = navController,
                        startDestination = Screen.CakeListScreen.route
                    ) {
                        composable(route = Screen.CakeListScreen.route) {
                            CakeListingsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}



