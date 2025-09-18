package ru.n857l.quizgame.di

import ru.n857l.quizgame.RunAsync
import ru.n857l.quizgame.core.StringCache
import ru.n857l.quizgame.load.LoadRepository
import ru.n857l.quizgame.load.LoadViewModel
import ru.n857l.quizgame.load.ParseQuestionAndChoices
import ru.n857l.quizgame.load.Response
import ru.n857l.quizgame.load.UiObservable

class ProvideLoadViewModel(core: Core, next: ProvideViewModel) :
    AbstractProvideViewModel(core, next, LoadViewModel::class.java) {

    override fun module(): Module<*> = LoadModule(core)
}

class LoadModule(private val core: Core) : Module<LoadViewModel> {

    override fun viewModel(): LoadViewModel {
        val responseDefault = Response(-1, emptyList())
        val defaultResponse = core.gson.toJson(responseDefault)
        return LoadViewModel(
            LoadRepository.Base(
                ParseQuestionAndChoices.Base(core.gson),
                StringCache.Base(core.sharedPreferences, "response_data", defaultResponse)
            ),
            UiObservable.Base(),
            RunAsync.Base()
        )
    }
}