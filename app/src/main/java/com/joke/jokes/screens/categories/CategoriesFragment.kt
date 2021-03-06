package com.joke.jokes.screens.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.`fun`.joke.jokes.databinding.FragmentCategoriesBinding
import com.joke.jokes.api.CategoryLoader
import com.joke.jokes.api.CategoryLoaderListener
import com.joke.jokes.replaceFragment
import com.joke.jokes.screens.joke.JokeFragment

class CategoriesFragment : Fragment(), CategoryLoaderListener {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private val adapter = CategoryAdapter()
    private val categoriesList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.categoriesFragmentRecyclerView.adapter = adapter
        val categories = CategoryLoader(this).loadCategory()
        renderCategory()
    }


    private fun renderCategory() {
//        binding.categoriesFragmentRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.categoriesFragmentRecyclerView.adapter = adapter
        adapter.setCategory(categoriesList)
    }

    private fun showDetailsOfCategory() {
        replaceFragment(JokeFragment.newInstance())
    }

    companion object {
        fun newInstance() = CategoriesFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onLoaded(categories: List<String>) {
    }

    override fun onFailed(throwable: Throwable) {
        TODO("Not yet implemented")
    }
}