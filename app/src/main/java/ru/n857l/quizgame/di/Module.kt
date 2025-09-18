package ru.n857l.quizgame.di

import ru.n857l.quizgame.core.MyViewModel

interface Module<T : MyViewModel> {

    fun viewModel(): T
}