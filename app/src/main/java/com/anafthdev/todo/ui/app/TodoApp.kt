package com.anafthdev.todo.ui.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
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
import com.anafthdev.todo.data.model.Category
import com.anafthdev.todo.theme.ToDoTheme
import com.anafthdev.todo.ui.category.CategoryScreen
import com.anafthdev.todo.ui.category.CategoryViewModel
import com.anafthdev.todo.ui.dashboard.DashboardScreen
import com.anafthdev.todo.ui.dashboard.DashboardViewModel
import com.anafthdev.todo.ui.new_edit_category.NewEditCategoryScreen
import com.anafthdev.todo.ui.new_edit_category.NewEditCategoryViewModel
import com.anafthdev.todo.ui.new_todo.NewTodoScreen
import com.anafthdev.todo.ui.new_todo.NewTodoViewModel
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalMaterialApi::class)
@Composable
fun TodoApp(
    inDarkTheme: Boolean = false,
    viewModel: TodoAppViewModel
) {

    val density = LocalDensity.current

    val bottomSheetNavigator = remember {
        BottomSheetNavigator(
            ModalBottomSheetState(
                density = density,
                initialValue = ModalBottomSheetValue.Hidden,
                isSkipHalfExpanded = true
            )
        )
    }

    val systemUiController = rememberSystemUiController()
    val navController = rememberNavController(bottomSheetNavigator)
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
                drawerState = drawerState,
                drawerContent = {
                    DrawerContent(
                        selectedRoute = backStackEntry?.destination?.route ?: "",
                        categories = viewModel.categories,
                        onDestinationClicked = { destination ->
                            navController.navigate(
                                route = destination.route,
                            ) {
                                popUpTo(TopLevelDestinations.Home.dashboard.route) {
                                    saveState = true
                                }

                                launchSingleTop = true
                                restoreState = true
                            }

                            scope.launch {
                                drawerState.close()
                            }
                        },
                        onDismissRequest = {
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    )
                }
            ) {
                ModalBottomSheetLayout(
                    bottomSheetNavigator = bottomSheetNavigator,
                    sheetBackgroundColor = MaterialTheme.colorScheme.surface,
                    sheetContentColor = MaterialTheme.colorScheme.onSurface,
                    scrimColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.32f),
                    sheetShape = RoundedCornerShape(
                        topStartPercent = 8,
                        topEndPercent = 8
                    )
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
                                val mViewModel = hiltViewModel<DashboardViewModel>(backEntry)

                                DashboardScreen(
                                    navController = navController,
                                    viewModel = mViewModel,
                                    onNavigationIconClicked = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }
                                )
                            }

                            composable(TopLevelDestinations.Home.category.route) { backEntry ->
                                val mViewModel = hiltViewModel<CategoryViewModel>(backEntry)

                                CategoryScreen(
                                    navController = navController,
                                    viewModel = mViewModel,
                                    onNavigationIconClicked = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }
                                )
                            }

                            bottomSheet(TopLevelDestinations.Home.newTodo.route) { backEntry ->
                                val mViewModel = hiltViewModel<NewTodoViewModel>()

                                NewTodoScreen(
                                    navController = navController,
                                    viewModel = mViewModel
                                )
                            }

                            bottomSheet(
                                route = TopLevelDestinations.Home.newEditCategory.route,
                                arguments = TopLevelDestinations.Home.newEditCategory.arguments
                            ) { backEntry ->
                                val mViewModel = hiltViewModel<NewEditCategoryViewModel>(backEntry)

                                NewEditCategoryScreen(
                                    navController = navController,
                                    viewModel = mViewModel
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerContent(
    selectedRoute: String,
    categories: List<Category>,
    onDestinationClicked: (TopLevelDestination) -> Unit,
    onDismissRequest: () -> Unit,
) {
    ModalDrawerSheet {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = onDismissRequest) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = "Close"
                            )
                        }
                    }
                )
            }

            items(
                items = NavigationDrawerDestination,
                key = { item -> item.route }
            ) { destination ->
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
                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                )
            }

            item {
                HorizontalDivider()
            }

            items(
                items = categories,
                key = { item -> item.id }
            ) { category ->

            }
        }
    }
}
