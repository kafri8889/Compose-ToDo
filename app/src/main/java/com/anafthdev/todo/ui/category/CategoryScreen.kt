package com.anafthdev.todo.ui.category

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anafthdev.todo.R
import com.anafthdev.todo.data.DestinationArgument
import com.anafthdev.todo.data.TopLevelDestinations
import com.anafthdev.todo.data.datasource.local.LocalCategoryDataProvider
import com.anafthdev.todo.foundation.uicomponent.CategoryItem

private val menuItems: Array<Pair<String, Int>>
    @Composable
    get() = arrayOf(
        stringResource(id = R.string.edit) to R.drawable.ic_edit,
        stringResource(id = R.string.delete) to R.drawable.ic_trash
    )

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CategoryScreen(
    navController: NavController,
    viewModel: CategoryViewModel,
    onNavigationIconClicked: () -> Unit
) {

    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    if (viewModel.categoryToDelete != null) {
        AlertDialog(
            onDismissRequest = {
                viewModel.categoryToDelete = null
            },
            title = {
                Text(stringResource(id = R.string.delete_category))
            },
            text = {
                Text(stringResource(id = R.string.delete_category_msg))
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_trash),
                    contentDescription = null
                )
            },
            confirmButton = {
                Button(onClick = viewModel::deleteCategory) {
                    Text(stringResource(id = R.string.delete))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        viewModel.categoryToDelete = null
                    }
                ) {
                    Text(stringResource(id = R.string.cancel))
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                scrollBehavior = topAppBarScrollBehavior,
                title = {
                    Text(stringResource(id = TopLevelDestinations.Home.category.name!!))
                },
                navigationIcon = {
                    IconButton(onClick = onNavigationIconClicked) {
                        Icon(
                            imageVector = Icons.Rounded.Menu,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        TopLevelDestinations.Home.newEditCategory.createRoute(
                            DestinationArgument.IS_EDIT to false
                        ).route
                    )
                },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "New Category"
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
                items = viewModel.categories,
                key = { item -> item.id }
            ) { category ->
                var expanded by remember { mutableStateOf(false) }

                Box {
                    CategoryItem(
                        category = category,
                        modifier = Modifier
                            .fillMaxWidth(0.96f)
                            .combinedClickable(
                                indication = rememberRipple(),
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = {
                                    navController.navigate(
                                        TopLevelDestinations.Home.categoryWithTodo.createRoute(
                                            DestinationArgument.CATEGORY_ID to category.id
                                        ).route
                                    )
                                },
                                onLongClick = {
                                    if (category.id != LocalCategoryDataProvider.notCategorized.id) {
                                        expanded = !expanded
                                    }
                                }
                            )
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {
                        for ((i, pair) in menuItems.withIndex()) {
                            DropdownMenuItem(
                                text = {
                                    Text(pair.first)
                                },
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(id = pair.second),
                                        contentDescription = null
                                    )
                                },
                                onClick = {
                                    expanded = false
                                    when (i) {
                                        0 -> navController.navigate(
                                            TopLevelDestinations.Home.newEditCategory.createRoute(
                                                DestinationArgument.IS_EDIT to true,
                                                DestinationArgument.CATEGORY_ID to category.id,
                                            ).route
                                        )
                                        1 -> viewModel.categoryToDelete = category
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
