package com.nyx.textquests.repository

import android.content.res.AssetManager
import com.google.gson.Gson
import com.nyx.textquests.Graphs
import com.nyx.textquests.repository.models.GraphNode
import org.json.JSONArray
import org.json.JSONObject


class GraphLocalRepository(val assetManager: AssetManager) : GraphRepository {

    override fun getGraphNode(graphName: String, nodeId: Int): GraphNode? {
        val inputStream = assetManager.open(graphName)
        val stringBuilder = StringBuilder()
        inputStream.bufferedReader().useLines {
            it.forEach {
                stringBuilder.append(it)
            }
        }

        val json: String = stringBuilder.toString()
        val objectsList = mutableListOf<GraphNode>()

        val arr = JSONArray(json)
        for (i in 0 until arr.length()) {
            val obj: JSONObject = arr.getJSONObject(i)
            val objectClass = convertJsonToDataClass<GraphNode>(obj)

            if (objectClass != null) {
                objectsList.add(objectClass)
            }
        }

        return objectsList.firstOrNull { it.id == nodeId }
    }
}

inline fun <reified T> convertJsonToDataClass(jsonObject: JSONObject): T? {
    return try {
        val gson = Gson()
        gson.fromJson(jsonObject.toString(), T::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}