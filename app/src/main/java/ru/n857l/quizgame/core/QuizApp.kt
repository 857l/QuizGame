package ru.n857l.quizgame.core

import android.app.Application
import ru.n857l.quizgame.MyViewModel
import ru.n857l.quizgame.di.ClearViewModel
import ru.n857l.quizgame.di.Core
import ru.n857l.quizgame.di.ManageViewModels
import ru.n857l.quizgame.di.ProvideViewModel

class QuizApp : Application(), ProvideViewModel {

    private lateinit var factory: ManageViewModels

    override fun onCreate() {
        super.onCreate()

        val clearViewModel = object : ClearViewModel {
            override fun clear(viewModelClass: Class<out MyViewModel>) =
                factory.clear(viewModelClass)
        }
        val make = ProvideViewModel.Make(Core(this, clearViewModel))
        factory = ManageViewModels.Factory(make)
    }

    override fun <T : MyViewModel> makeViewModel(clasz: Class<T>): T =
        factory.makeViewModel(clasz)
}