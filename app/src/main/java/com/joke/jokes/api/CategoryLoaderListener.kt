package com.joke.jokes.api

interface CategoryLoaderListener {
    fun onLoaded(categories: List<String>)
    fun onFailed(throwable: Throwable)
}