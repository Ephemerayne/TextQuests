package com.nyx.textquests.screens.textquest.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.nyx.textquests.utils.toStable
import com.nyx.textquests.utils.viewModelFactory
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emptyFlow

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
        onOptionClick = { viewModel.clickOnOption(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ViewScreen(
    node: GraphNode?,
    onOptionClick: (option: GraphOption) -> Unit,
) {
    if (node == null) return

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = node.text, textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        key(node.options) {
            LazyColumn {
                items(items = node.options) { option ->
                    Card(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                            .height(60.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent,
                        ),
                        border = BorderStroke(1.dp, Color.LightGray),
                        onClick = {
                            onOptionClick(option)
                        }
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
    }
}

@Stable
private class MutableInteractionSourceImpl : MutableInteractionSource {
    // TODO: consider replay for new indication instances during events?
    override val interactions = MutableSharedFlow<Interaction>(
        extraBufferCapacity = 16,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    override suspend fun emit(interaction: Interaction) {
        interactions.emit(interaction)
    }

    override fun tryEmit(interaction: Interaction): Boolean {
        return interactions.tryEmit(interaction)
    }
}
