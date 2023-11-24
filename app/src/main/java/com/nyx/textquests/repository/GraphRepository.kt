package com.nyx.textquests.repository

import com.nyx.textquests.repository.models.GraphNode

interface GraphRepository {
    fun getGraphNode( graphName: String, nodeId: Int = 0): GraphNode?
}