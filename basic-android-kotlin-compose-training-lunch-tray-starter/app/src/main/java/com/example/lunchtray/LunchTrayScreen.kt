/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.*

// TODO: Screen enum
enum class LunchTrayScreen(@StringRes val currentScreenId: Int) {
    START(currentScreenId = R.string.start_order),
    ENTREE_MENU(currentScreenId = R.string.choose_entree),
    SIDE_DISH_MENU(currentScreenId = R.string.choose_side_dish),
    ACCOMPANIMENT_MENU(currentScreenId = R.string.choose_accompaniment),
    CHECKOUT(currentScreenId = R.string.order_checkout)
}
// TODO: AppBar

@Composable
fun LunchTrayAppBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = LunchTrayScreen.valueOf(currentScreen).currentScreenId))
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun LunchTrayApp(modifier: Modifier = Modifier) {
    // Create Controller and initialization
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            LunchTrayAppBar(
                currentScreen = backStackEntry?.destination?.route ?: LunchTrayScreen.START.name,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.START.name,
            modifier = modifier
        ) {
            composable(route = LunchTrayScreen.START.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = {
                        navController.navigate(LunchTrayScreen.ENTREE_MENU.name)
                    }
                )
            }
            composable(route = LunchTrayScreen.ENTREE_MENU.name) {
                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = {
                        cancelAndResetFlow(viewModel, navController)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.SIDE_DISH_MENU.name)
                    },
                    onSelectionChanged = { newItem ->
                        viewModel.updateEntree(newItem)
                    }
                )
            }
            composable(route = LunchTrayScreen.SIDE_DISH_MENU.name) {
                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = {
                        cancelAndResetFlow(viewModel, navController)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.ACCOMPANIMENT_MENU.name)
                    },
                    onSelectionChanged = { newSideDish ->
                        viewModel.updateSideDish(newSideDish)
                    }
                )
            }
            composable(route = LunchTrayScreen.ACCOMPANIMENT_MENU.name) {
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = {
                        cancelAndResetFlow(viewModel, navController)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.CHECKOUT.name)
                    },
                    onSelectionChanged = { newAccompaniment ->
                        viewModel.updateAccompaniment(newAccompaniment)
                    }
                )
            }
            composable(route = LunchTrayScreen.CHECKOUT.name) {
                val currentContext = LocalContext.current
                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = {
                        Toast.makeText(currentContext,"Order Success",Toast.LENGTH_SHORT).show()
                        cancelAndResetFlow(viewModel, navController)
                    },
                    onCancelButtonClicked = { cancelAndResetFlow(viewModel, navController) }
                )
            }
        }
    }
}

fun cancelAndResetFlow(viewModel: OrderViewModel, navHostController: NavHostController) {
    viewModel.resetOrder()
    navHostController.popBackStack(LunchTrayScreen.START.name, inclusive = false)
}
