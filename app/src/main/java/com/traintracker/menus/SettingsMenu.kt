package com.traintracker.menus

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Slider
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
fun SettingsMenu(
    settings: Settings,
    settingsViewModel: SettingsViewModel = viewModel(factory = SettingsViewModel.Factory),
    onScreenChange: (List<String>) -> Unit,
    screenList: List<String>
) {

    val modifier = Modifier
        .fillMaxWidth()
        .background(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = RoundedCornerShape(16.dp)
        )
        .padding(vertical = 8.dp)
        .padding(horizontal = 8.dp)

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            item {

                Text(
                    text = stringResource(R.string.theme_toggle),
                    modifier = modifier
                        .clickable(
                            onClick = {
                                settingsViewModel.toggleTheme(!settings.darkTheme)
                            })
                    ,
                    fontSize = (20 * settings.textSizeMultiplier).sp
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .padding(horizontal = 8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
                        )
                        .padding(0.5.dp)
                )
                Text(
                    text = stringResource(R.string.language_change),
                    modifier = modifier
                        .clickable(
                            onClick = {
                                Log.d("Settings", "Language: ${settings.language}")
                            }),
                    fontSize = (20 * settings.textSizeMultiplier).sp
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .padding(horizontal = 8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
                        )
                        .padding(0.5.dp)
                )
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        modifier = Modifier
                            .padding(4.dp)
                            .clickable(onClick = {
                                settingsViewModel.setTextSizeMultiplier(1f)
                            })
                            .weight(1f),
                        contentDescription = stringResource(R.string.text_reset)
                    )
                    Slider(
                        value = settings.textSizeMultiplier,
                        onValueChange = {
                            settingsViewModel.setTextSizeMultiplier(it)
                        },
                        valueRange = 0.5f..2.5f,
                        steps = 18,
                        modifier = Modifier
                            .weight(8f),

                        )
                    Text(
                        text = settings.textSizeMultiplier.toString().take(3),
                        modifier = Modifier
                            .weight(1f),
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .padding(horizontal = 8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
                        )
                        .padding(0.5.dp)
                )
                Column(
                    modifier = modifier
                ){
                    Text(
                        text = stringResource(R.string.icon_style) + ":",
                        fontSize = (20 * settings.textSizeMultiplier).sp
                    )
                    Spacer(
                        modifier = Modifier
                            .size(4.dp)
                    )
                        Row(
                            modifier = Modifier
                                .height(64.dp),
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            NavigationBarItem(
                                selected = settings.iconStyle == 0,
                                onClick = {
                                    settingsViewModel.setIconStyle(0)
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Home,
                                        contentDescription = stringResource(R.string.home)
                                    )
                                },
                                label = {
                                    Text(
                                        text = stringResource(R.string.home),
                                        fontSize = (12 * settings.textSizeMultiplier).sp
                                    )
                                }
                            )
                            NavigationBarItem(
                                selected = settings.iconStyle == 1,
                                onClick = {
                                    settingsViewModel.setIconStyle(1)
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Home,
                                        contentDescription = stringResource(R.string.home)
                                    )
                                }
                            )
                            NavigationBarItem(
                                selected = settings.iconStyle == 2,
                                onClick = {
                                    settingsViewModel.setIconStyle(2)
                                },
                                icon = {
                                },
                                label = {
                                    Text(
                                        text = stringResource(R.string.home),
                                        fontSize = (12 * settings.textSizeMultiplier).sp
                                    )
                                }
                            )
                    }
                }
            }
        }
    }
}
