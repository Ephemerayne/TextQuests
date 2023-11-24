package com.nyx.textquests.screens.textquest.impl

import androidx.lifecycle.ViewModel
import com.nyx.textquests.Graphs
import com.nyx.textquests.repository.GraphRepository
import com.nyx.textquests.repository.models.GraphNode
import com.nyx.textquests.repository.models.GraphOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TextQuestViewModel(private val graphRepository: GraphRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(TextQuestUiState())
    val uiState: StateFlow<TextQuestUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(
            currentNode = graphRepository.getGraphNode(_uiState.value.currentGraph),
            currentGraph = Graphs.PROLOGUE
        )
    }

    fun clickOnOption(option: GraphOption) {
        _uiState.value = _uiState.value.copy(
            currentNode = getGraphNode(option),
            currentGraph = option.goToGraph ?: _uiState.value.currentGraph
        )
    }

    private fun getGraphNode(option: GraphOption): GraphNode? {
        if (option.goToGraph != null) {
            _uiState.value = _uiState.value.copy(currentGraph = option.goToGraph)
        }

        return graphRepository.getGraphNode(_uiState.value.currentGraph, option.targetNodeId)
    }
}