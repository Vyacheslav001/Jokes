package com.`fun`.joke.jokes

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.`fun`.joke.jokes.databinding.FragmentDetailsBinding
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class DetailsFragment : Fragment(), JokeLoaderListener {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var listener: JokeLoaderListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Работа с сетью. Запрос шутки по выбранной категории

        //URL адре некоррректный
        val url = URL("https://api.chucknorris.io/jokes/random?category={category}")
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
            val jokeDTO = Gson().fromJson(reader, JokeDTO::class.java)
            val handler = Handler(Looper.getMainLooper())
            handler.post { listener.onLoaded(jokeDTO) }
            urlConnection.disconnect()
        }.start()
    }

    companion object {
        fun newInstance() = DetailsFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onLoaded(jokeDTO: JokeDTO) {
        TODO("Not yet implemented")
    }

    override fun onFailed(throwable: Throwable) {
        TODO("Not yet implemented")
    }
}