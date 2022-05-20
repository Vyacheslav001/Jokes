package com.`fun`.joke.jokes

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
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
            //Нужно передать ключ:
            urlConnection.addRequestProperty(
                "X-Yandex-API-Key",
                "01050e1b-58f7-43ea-8aca-c4c58af60360"
            )
            urlConnection.readTimeout = 10000
            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val categoryDTO = Gson().fromJson(reader, CategoryDTO::class.java)
            val handler = Handler(Looper.getMainLooper())
            handler.post { listener.onLoaded(categoryDTO) }
            urlConnection.disconnect()
        }.start()
    }
}