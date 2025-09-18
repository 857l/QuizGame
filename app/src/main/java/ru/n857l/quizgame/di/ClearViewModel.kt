package ru.n857l.quizgame.di

import ru.n857l.quizgame.core.MyViewModel

interface ClearViewModel {

    fun clear(viewModelClass: Class<out MyViewModel>)
}