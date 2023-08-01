package com.anafthdev.todo.ui.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.anafthdev.todo.data.TopLevelDestinations
import com.anafthdev.todo.theme.ToDoTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TodoApp(
    inDarkTheme: Boolean = isSystemInDarkTheme()
) {

    val systemUiController = rememberSystemUiController()
    val navController = rememberNavController()

    ToDoTheme {
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = !inDarkTheme
            )
        }

        NavHost(
            navController = navController,
            startDestination = TopLevelDestinations.SignInOrSignUp.ROUTE,
        ) {

        }
    }
}
