package ru.n857l.quizgame.di

import ru.n857l.quizgame.MyViewModel

interface Module<T : MyViewModel> {

    fun viewModel(): T
}