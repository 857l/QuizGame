package ru.n857l.quizgame.stats

interface StatsRepository {

    fun stats(): Pair<Int, Int>
}