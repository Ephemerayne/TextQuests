package com.nyx.textquests

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.nyx.textquests.repository.models.GraphNode
import java.io.IOException
import java.io.InputStream

class LocalJSONParser {

    companion object {
        fun inputStreamToString(inputStream: InputStream): String {
            return try {
                val bytes = ByteArray(inputStream.available())
                inputStream.read(bytes, 0, bytes.size)
                String(bytes)
            } catch (e: IOException) {
                println(e)
                ""
            }
        }
    }
}

inline fun <reified T> Context.getObjectFromJson(jsonFileName: String): T {
    val myJson = LocalJSONParser.inputStreamToString(assets.open(jsonFileName))

    // Use a TypeToken to capture the generic type information
    val typeToken = object : TypeToken<T>() {}.type

    return Gson().fromJson(myJson, typeToken)
}

inline fun <reified T> Context.getObjectListFromJson(jsonFileName: String): List<T> {
    val myJson = LocalJSONParser.inputStreamToString(assets.open(jsonFileName))

    // Use a TypeToken to capture the generic type information for the list
    val listTypeToken = object : TypeToken<List<T>>() {}.type

    return Gson().fromJson(myJson, listTypeToken)
}