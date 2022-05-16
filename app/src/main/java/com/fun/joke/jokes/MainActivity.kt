package com.`fun`.joke.jokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            changeFragment(MainFragment.newInstance())
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, MainFragment.newInstance()).commit()
    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("")
            .commit()
    }
}