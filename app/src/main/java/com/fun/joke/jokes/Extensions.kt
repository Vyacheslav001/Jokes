package com.`fun`.joke.jokes

import android.view.ViewGroup
import androidx.fragment.app.Fragment

fun Fragment.replaceFragment(fragment: Fragment){
    requireActivity().supportFragmentManager.beginTransaction()
        .replace((requireView().parent as ViewGroup).id, fragment)
        .addToBackStack("")
        .commit()
}
