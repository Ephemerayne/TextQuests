package com.nyx.textquests.screens.textquest.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nyx.textquests.repository.GraphLocalRepository
import com.nyx.textquests.repository.GraphRepository
import com.nyx.textquests.repository.models.GraphNode
import com.nyx.textquests.repository.models.GraphOption
import com.nyx.textquests.screens.textquest.api.TextQuestScreenNavigation
import com.nyx.textquests.screens.textquest.impl.TextQuestViewModel
import com.nyx.textquests.utils.viewModelFactory

@Composable
fun TextQuestScreen(
    repository: GraphRepository,
    screenNavigation: TextQuestScreenNavigation,
) {

    val viewModel: TextQuestViewModel =
        viewModel(factory = viewModelFactory { TextQuestViewModel(repository) })


    val uiState by viewModel.uiState.collectAsState()

    ViewScreen(
        node = uiState.currentNode,
        onOptionClick = { viewModel.clickOnOption(it) })

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ViewScreen(
    node: GraphNode?,
    onOptionClick: (option: GraphOption) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = node?.text.orEmpty(), textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        node?.options?.forEach { option ->
            Card(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                ),
                border = BorderStroke(1.dp, Color.LightGray),
                onClick = { onOptionClick(option) }
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .align(Alignment.CenterStart),
                        text = option.optionText,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}