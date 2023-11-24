package com.nyx.textquests.screens.textquest.impl

import com.nyx.textquests.Graphs
import com.nyx.textquests.repository.models.GraphNode

data class TextQuestUiState(
    val currentGraph : String = Graphs.PROLOGUE,
    var currentNode: GraphNode? = null,
)