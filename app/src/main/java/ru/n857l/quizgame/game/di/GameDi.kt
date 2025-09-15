package ru.n857l.quizgame.game.di

import ru.n857l.quizgame.core.IntCache
import ru.n857l.quizgame.core.StringCache
import ru.n857l.quizgame.di.AbstractProvideViewModel
import ru.n857l.quizgame.di.Core
import ru.n857l.quizgame.di.Module
import ru.n857l.quizgame.di.ProvideViewModel
import ru.n857l.quizgame.game.GameRepository
import ru.n857l.quizgame.game.GameViewModel
import ru.n857l.quizgame.load.ParseQuestionAndChoices
import ru.n857l.quizgame.load.Response

class GameModule(private val core: Core) : Module<GameViewModel> {

    override fun viewModel(): GameViewModel {
        val corrects = IntCache.Base(core.sharedPreferences, "corrects", 0)
        val incorrects = IntCache.Base(core.sharedPreferences, "incorrects", 0)


        val responseDefault = Response(-1, emptyList())
        val defaultResponse = core.gson.toJson(responseDefault)
        return GameViewModel(
            core.clearViewModel,
            GameRepository.Base(
                corrects,
                incorrects,
                IntCache.Base(core.sharedPreferences, "indexKey", 0),
                IntCache.Base(core.sharedPreferences, "userChoiceIndexKey", -1),
                StringCache.Base(core.sharedPreferences, "response_data", defaultResponse),
                ParseQuestionAndChoices.Base(core.gson)
            )
        )
    }
}

class ProvideGameViewModel(core: Core, next: ProvideViewModel) :
    AbstractProvideViewModel(core, next, GameViewModel::class.java) {

    override fun module(): Module<*> = GameModule(core)
}