package com.traintracker.menus


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.lifecycle.viewmodel.compose.viewModel
import com.traintracker.main.Settings
import com.traintracker.main.SettingsViewModel

@Composable
fun HomeMenu(
    settings: Settings,
    settingsViewModel: SettingsViewModel = viewModel(factory = SettingsViewModel.Factory),
    onScreenChange: (List<String>) -> Unit,
    screenList: List<String>
) {

}
