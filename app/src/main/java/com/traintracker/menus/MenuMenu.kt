package com.traintracker.menus

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.traintracker.main.Settings
import com.traintracker.main.SettingsViewModel

@Composable
fun MenuMenu(
    settings: Settings,
    settingsViewModel: SettingsViewModel = viewModel(factory = SettingsViewModel.Factory)
){

}