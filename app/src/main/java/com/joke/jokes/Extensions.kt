package com.joke.jokes

import androidx.fragment.app.Fragment

fun Fragment.replaceFragment(fragment: Fragment){
    requireActivity().supportFragmentManager.beginTransaction()
//        .replace((requireView().parent as ViewGroup).id, fragment)
        .replace(id, fragment)
        .addToBackStack("")
        .commit()
}
