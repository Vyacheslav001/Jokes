package com.`fun`.joke.jokes

interface CategoryLoaderListener {
    fun onLoaded(categories: List<String>)
    fun onFailed(throwable: Throwable)
}