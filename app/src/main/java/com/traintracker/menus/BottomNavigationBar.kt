package com.traintracker.menus


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.traintracker.R
import com.traintracker.main.Settings
import com.traintracker.main.SettingsViewModel

@Composable
fun BottomNavigationBar(
    settings: Settings,
    settingsViewModel: SettingsViewModel = viewModel(factory = SettingsViewModel.Factory),
    onScreenChange: (List<String>) -> Unit,
    screenList: List<String>
) {

    NavigationBar {
        NavigationBarItem(
            selected = screenList.last() == "home",
            onClick = {
                onScreenChange(listOf("home"))
                      },
            icon = {
                if (settings.iconStyle == 0 || settings.iconStyle == 1) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = stringResource(id = R.string.home)
                    )
                }
            },
            label = {
                if (settings.iconStyle == 0 || settings.iconStyle == 2) {
                    Text(
                        text = stringResource(id = R.string.home),
                        fontSize = (12 * settings.textSizeMultiplier).sp
                        )
                }
            }
        )
        NavigationBarItem(
            selected = screenList.last() == "menu",
            onClick = {
                onScreenChange(listOf("menu"))
            },
            icon = {
                if (settings.iconStyle == 0 || settings.iconStyle == 1) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = stringResource(id = R.string.menu)
                    )
                }
            },
            label = {
                if (settings.iconStyle == 0 || settings.iconStyle == 2) {
                    Text(
                        text = stringResource(id = R.string.menu),
                        fontSize = (12 * settings.textSizeMultiplier).sp
                    )
                }
            }
        )
        NavigationBarItem(
            selected = screenList.last() == "settings",
            onClick = {
                onScreenChange(listOf("settings"))
                      },
            icon = {
                if (settings.iconStyle == 0 || settings.iconStyle == 1) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = stringResource(id = R.string.settings)
                    )
                }
            },
            label = {
                if (settings.iconStyle == 0 || settings.iconStyle == 2) {
                    Text(
                        text = stringResource(id = R.string.settings),
                        fontSize = (12 * settings.textSizeMultiplier).sp
                        )
                }
            }
        )
    }
}