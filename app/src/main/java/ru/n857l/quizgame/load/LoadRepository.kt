package ru.n857l.quizgame.load

interface LoadRepository {

    fun load(resultCallback: (LoadResult) -> Unit)
}