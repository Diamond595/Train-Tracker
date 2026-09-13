package com.traintracker.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.traintracker.menus.BottomNavigationBar
import com.traintracker.menus.SettingsMenu
import com.traintracker.menus.HomeMenu
import com.traintracker.menus.MenuMenu
import com.traintracker.ui.theme.TrainTrackerTheme


class MainActivity : ComponentActivity() {
    val settingsViewModel: SettingsViewModel by viewModels { SettingsViewModel.Factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContent {
                val settings by settingsViewModel.settingsFlow.collectAsStateWithLifecycle(
                    initialValue = Settings(
                        darkTheme = isSystemInDarkTheme(),
                        language = Locale.current.language,
                        accessToken = "",
                        textSizeMultiplier = 1f,
                        iconStyle = 0
                    )
                )

                val screenList = remember { mutableStateListOf("home", "settings") }
                LaunchedEffect(screenList) {
                    if (screenList.size > 5) {
                        screenList.removeAt(0)
                    }
                }

                enableEdgeToEdge()
                TrainTrackerTheme(
                    darkTheme = settings.darkTheme
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                settings = settings,
                                onScreenChange = { screenList.clear(); screenList.addAll(it) },
                                screenList = screenList,
                            )
                        }
                    ) { innerPadding ->
                        Surface(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        ) {
                            when (screenList.last()) {
                                "home" -> HomeMenu(
                                    settings = settings,
                                    onScreenChange = { screenList.clear(); screenList.addAll(it) },
                                    screenList = screenList
                                )

                                "settings" -> SettingsMenu(
                                    settings = settings,
                                    onScreenChange = { screenList.clear(); screenList.addAll(it) },
                                    screenList = screenList
                                )
                                "menu" -> MenuMenu(
                                    settings = settings
                                )
                            }
                        }
                    }
                }
            }
    }
}