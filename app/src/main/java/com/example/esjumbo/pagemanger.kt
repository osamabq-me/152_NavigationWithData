package com.example.cobarouting.ui

import android.annotation.SuppressLint
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dumdumteaapp.SecondPage
import com.example.esjumbo.HalamanHome
import com.example.esjumbo.OrderViewModel
import com.example.esjumbo.R
import com.example.esjumbo.data.SumberData.flavor
import com.example.navigation.FirstPage


enum class PageManager {
    Home,
    Rasa,
    Summary
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IceTeaAppbar(
    isBackNav: Boolean,
    upNav: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (isBackNav) {
                IconButton(onClick = upNav) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IceTeaApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        topBar = {
            IceTeaAppbar(
                isBackNav = false,
                upNav = {}
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.stateUi.collectAsState()
        NavHost(
            navController = navController,
            startDestination = PageManager.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = PageManager.Home.name) {
                HalamanHome(
                    onNextButtonClicked = { navController.navigate(PageManager.Rasa.name) }
                )
            }
            composable(route = PageManager.Rasa.name) {
                val context = LocalContext.current
                FirstPage(
                    pilihanRasa = flavor.map { id -> context.resources.getString(id) },
                    onSelectionChanged = { viewModel.setRasa(it) },
                    onConfirmButtonClicked = { viewModel.setJumlah(it) },
                    onNextButtonClicked = { navController.navigate(PageManager.Summary.name) },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToHome(
                            viewModel,
                            navController
                        )
                    })
            }
            composable(route = PageManager.Summary.name) {
                SecondPage(orderUIState = uiState, onCancelButtonClicked = {
                    cancelOrderAndNavigateToRasa(
                        navController
                    )
                })
            }
        }
    }
}

private fun cancelOrderAndNavigateToHome(
    viewModel: OrderViewModel,
    navController: NavHostController,
) {
    viewModel.resetOrder()
    navController.popBackStack(PageManager.Home.name, inclusive = false)
}

private fun cancelOrderAndNavigateToRasa(
    navController: NavHostController,
) {
    navController.popBackStack(PageManager.Rasa.name, inclusive = false)
}