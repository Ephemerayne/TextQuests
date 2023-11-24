package com.nyx.textquests.repository.models

data class GraphNode(
    val id: Int,
    val text: String,
    val options: List<GraphOption> = listOf(),
)

data class GraphOption(
    val targetNodeId: Int,
    val optionText: String,
    val goToGraph: String? = null
)