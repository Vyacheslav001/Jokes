package com.`fun`.joke.jokes

interface CategoryLoaderListener {
    fun onLoaded(category: CategoryDTO)
    fun onFailed(throwable: Throwable)
}