package com.`fun`.joke.jokes

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.`fun`.joke.jokes.databinding.FragmentMainBinding
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainFragment : Fragment(), CategoryLoaderListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var listener: CategoryLoaderListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailsButton.setOnClickListener {
           showDetails()

            //Работа с сетью. Запрос категорий шуток

            //URL адре некоррректный
            val url = URL("https://api.chucknorris.io/jokes/categories")
            Thread {
                val urlConnection = url.openConnection() as HttpsURLConnection
                urlConnection.requestMethod = "GET"
                //Нужно передать ключ:
                urlConnection.addRequestProperty("X-Yandex-API-Key", "01050e1b-58f7-43ea-8aca-c4c58af60360")
                urlConnection.readTimeout = 10000
                val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val categoryDTO = Gson().fromJson(reader, CategoryDTO::class.java)
                val handler = Handler(Looper.getMainLooper())
                handler.post { listener.onLoaded(categoryDTO) }
                urlConnection.disconnect()
            }.start()

            // Дубликат кода работы с сетью, но в отдельном классе
//            CategoryLoader(this).loadCategory()
        }
    }

    private fun showDetails(){
        replaceFragment(DetailsFragment.newInstance())
    }


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onLoaded(category: CategoryDTO) {
        TODO("Not yet implemented")
    }

    override fun onFailed(throwable: Throwable) {
        TODO("Not yet implemented")
    }
}