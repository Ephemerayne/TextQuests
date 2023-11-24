package com.nyx.textquests.screens.main.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nyx.textquests.navigation.MainScreenNavigationImpl
import com.nyx.textquests.screens.main.api.MainScreenNavigation
import com.nyx.textquests.screens.main.impl.MainViewModel
import com.nyx.textquests.screens.main.impl.MenuItem
import com.nyx.textquests.screens.main.impl.NavigationEvent
import com.nyx.textquests.screens.textquest.impl.TextQuestViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class MenuItemModel(
    val text: String,
    val menuItem: MenuItem,
)

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
    screenNavigation: MainScreenNavigation,
) {

    val menuItems = remember {
        listOf(
            MenuItemModel("Новая игра", MenuItem.NEW),
            MenuItemModel("Загрузить", MenuItem.LOAD),
            MenuItemModel("Выход", MenuItem.EXIT)
        )
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        menuItems.forEach { item ->
            MenuCardItem(
                text = item.text,
                onClick = {
                    viewModel.onMenuItemClick(item.menuItem, screenNavigation)
                })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MenuCardItem(text: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(60.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        border = BorderStroke(1.dp, Color.LightGray),
        onClick = onClick
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.Center),
                text = text,
                textAlign = TextAlign.Center
            )
        }
    }
}