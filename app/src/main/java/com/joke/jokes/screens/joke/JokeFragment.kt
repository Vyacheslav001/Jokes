package com.joke.jokes.screens.joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.`fun`.joke.jokes.databinding.FragmentJokeBinding

class JokeFragment : Fragment() {

    private var _binding: FragmentJokeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJokeBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = JokeFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}