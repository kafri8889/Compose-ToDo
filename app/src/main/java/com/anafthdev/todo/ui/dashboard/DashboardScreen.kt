package com.anafthdev.todo.ui.dashboard

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anafthdev.todo.R
import com.anafthdev.todo.data.TopLevelDestinations
import com.anafthdev.todo.foundation.uicomponent.TodoWithCategoryItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel,
    onNavigationIconClicked: () -> Unit
) {

    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    
    Scaffold(
        topBar = {
            TopAppBar(
                scrollBehavior = topAppBarScrollBehavior,
                title = {
                    Text(stringResource(id = TopLevelDestinations.Home.dashboard.name!!))
                },
                navigationIcon = {
                    IconButton(onClick = onNavigationIconClicked) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(TopLevelDestinations.Home.newTodo.route)
                },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "New ToDo"
                )
            }
        },
        modifier = Modifier
            .systemBarsPadding()
            .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
    ) { scaffoldPadding ->
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .padding(scaffoldPadding)
        ) {
            items(
                items = viewModel.todoWithCategory,
                key = { item -> item.todo.id }
            ) { todoWithCategory ->
                TodoWithCategoryItem(
                    todoWithCategory = todoWithCategory,
                    onCheckedChange = { checked ->
                        viewModel.updateTodo(todoWithCategory.todo.copy(finished = checked))
                    },
                    onClick = {

                    },
                    modifier = Modifier
                        .animateItemPlacement(tween(256))
                )
            }

            if (viewModel.completedTodoWithCategory.isNotEmpty()) {
                item {
                    Text(
                        text = stringResource(id = R.string.completed),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement(tween(256))
                    )
                }
            }

            items(
                items = viewModel.completedTodoWithCategory,
                key = { item -> item.todo.id }
            ) { todoWithCategory ->
                TodoWithCategoryItem(
                    todoWithCategory = todoWithCategory,
                    onCheckedChange = { checked ->
                        viewModel.updateTodo(todoWithCategory.todo.copy(finished = checked))
                    },
                    onClick = {

                    },
                    modifier = Modifier
                        .animateItemPlacement(tween(256))
                )
            }
        }
    }

}
