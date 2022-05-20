package com.`fun`.joke.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.`fun`.joke.jokes.databinding.FragmentMainBinding

class MainFragment : Fragment(), CategoryLoaderListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

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

            CategoryLoader(this).loadCategory()
        }
    }

    private fun showDetails() {
        replaceFragment(DetailsFragment.newInstance())
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onLoaded(categories: List<String>) {
        showDetails()
    }

    override fun onFailed(throwable: Throwable) {
        TODO("Not yet implemented")
    }
}