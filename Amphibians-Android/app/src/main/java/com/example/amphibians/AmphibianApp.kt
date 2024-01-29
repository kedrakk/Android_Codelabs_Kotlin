package com.example.amphibians

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.ui.screens.HomeScreen
import com.example.amphibians.viewModel.AmphibianViewModel

@Composable
fun AmphibiansApp() {
    val amphibianViewModel:AmphibianViewModel = viewModel(factory = AmphibianViewModel.Factory)
    HomeScreen(amphibianUIState = amphibianViewModel.amphibianUIState)
}