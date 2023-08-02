package com.anafthdev.todo.ui.app

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.anafthdev.todo.data.NavigationDrawerDestination
import com.anafthdev.todo.data.TopLevelDestination
import com.anafthdev.todo.data.TopLevelDestinations
import com.anafthdev.todo.theme.ToDoTheme
import com.anafthdev.todo.ui.dashboard.DashboardScreen
import com.anafthdev.todo.ui.dashboard.DashboardViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun TodoApp(
    inDarkTheme: Boolean = false
) {

    val systemUiController = rememberSystemUiController()
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val backStackEntry by navController.currentBackStackEntryAsState()

    ToDoTheme(darkTheme = inDarkTheme) {
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = !inDarkTheme
            )
        }

        Surface {
            ModalNavigationDrawer(
                drawerContent = {
                    DrawerContent(
                        selectedRoute = backStackEntry?.destination?.route ?: "",
                        onDestinationClicked = { destination ->
                            navController.navigate(destination.route)
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    )
                }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = TopLevelDestinations.Home.ROUTE,
                ) {
                    navigation(
                        startDestination = TopLevelDestinations.Home.dashboard.route,
                        route = TopLevelDestinations.Home.ROUTE
                    ) {
                        composable(TopLevelDestinations.Home.dashboard.route) { backEntry ->
                            val viewModel = hiltViewModel<DashboardViewModel>(backEntry)

                            DashboardScreen(
                                navController = navController,
                                viewModel = viewModel,
                                onNavigationIconClicked = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DrawerContent(
    selectedRoute: String,
    onDestinationClicked: (TopLevelDestination) -> Unit
) {
    ModalDrawerSheet {
        Spacer(Modifier.height(12.dp))

        for (destination in NavigationDrawerDestination) {
            NavigationDrawerItem(
                selected = selectedRoute == destination.route,
                label = {
                    Text(stringResource(id = destination.name!!))
                },
                icon = {
                    Icon(
                        painter = painterResource(id = destination.icon!!),
                        contentDescription = null
                    )
                },
                onClick = {
                    onDestinationClicked(destination)
                }
            )
        }
    }
}
