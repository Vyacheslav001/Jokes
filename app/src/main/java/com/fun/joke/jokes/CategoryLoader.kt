package com.`fun`.joke.jokes

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class CategoryLoader(private val listener: CategoryLoaderListener) {
    fun loadCategory() {
        val url = URL("https://api.chucknorris.io/jokes/categories")
        Thread {
            val urlConnection = url.openConnection() as HttpsURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.readTimeout = 10000
            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val listClass = object : TypeToken<List<String>>() {}.type
            val categories: ArrayList<String> = Gson().fromJson(reader, listClass)
            val handler = Handler(Looper.getMainLooper())
            handler.post { listener.onLoaded(categories) }
            urlConnection.disconnect()
        }.start()
    }
}