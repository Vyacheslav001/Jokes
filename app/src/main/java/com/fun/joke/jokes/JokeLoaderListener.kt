package com.`fun`.joke.jokes

import com.`fun`.joke.jokes.JokeDTO

interface JokeLoaderListener {
    fun onLoaded(jokeDTO: JokeDTO)
    fun onFailed(throwable: Throwable)
}