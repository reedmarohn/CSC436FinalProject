@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.lab3nav

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lab3nav.ui.FormScreen
import com.example.lab3nav.ui.InventoryViewModel
import com.example.lab3nav.ui.ListScreen
import com.example.lab3nav.ui.StartScreen
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner

enum class InventoryScreen(@StringRes val title: Int) {
        Start(title = R.string.app_name),
        Form(title = R.string.FillForm),
        List(title = R.string.ListView)
}
@Composable
fun InventoryAppBar(
    currentScreen: InventoryScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
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
fun InventoryApp(
        viewModel: InventoryViewModel = viewModel(),
        navController: NavHostController = rememberNavController(),
        scanner : GmsBarcodeScanner
)
{
  // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = InventoryScreen.valueOf(
        backStackEntry?.destination?.route ?: InventoryScreen.Start.name
    )

    Scaffold(
        topBar = {
            InventoryAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = InventoryScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
        /************START***********/
            composable(route = InventoryScreen.Start.name) {
                StartScreen(
                    viewModel = viewModel,
                    onNewItemClicked = {
                        navController.navigate(InventoryScreen.Form.name)
                    },
                    onViewAllClicked = {
                        navController.navigate((InventoryScreen.List.name))
                    }
                    ,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
/**********FORM*************/
            composable(route = InventoryScreen.Form.name) {
                FormScreen(
                    model = viewModel,
                    scanner = scanner,
                    onNextButtonClicked = {
                        viewModel.saveProduct()
                        viewModel.resetProduct()
                        navController.navigate(InventoryScreen.List.name) },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    onScannerExit = {
                        navController.navigate(InventoryScreen.Form.name)
                    },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            /***********LIST************/
            composable(route = InventoryScreen.List.name) {
                            ListScreen(
                                prodList = viewModel.productList,
                                onDoneButtonClicked = {
                                    navController.navigate(InventoryScreen.Start.name)
                                },
                                modifier = Modifier.fillMaxHeight()
                            )
                        }
        }
    }
}

fun cancelOrderAndNavigateToStart(viewModel: InventoryViewModel, navController: NavHostController) {
    viewModel.resetProduct()
    navController.popBackStack(InventoryScreen.Start.name, inclusive = false)
}