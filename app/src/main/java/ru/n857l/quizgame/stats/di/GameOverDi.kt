package ru.n857l.quizgame.stats.di

import ru.n857l.quizgame.core.IntCache
import ru.n857l.quizgame.di.AbstractProvideViewModel
import ru.n857l.quizgame.di.Core
import ru.n857l.quizgame.di.Module
import ru.n857l.quizgame.di.ProvideViewModel
import ru.n857l.quizgame.stats.GameOverViewModel
import ru.n857l.quizgame.stats.StatsRepository

class GameOverModule(private val core: Core) : Module<GameOverViewModel> {

    override fun viewModel(): GameOverViewModel {
        val corrects = IntCache.Base(core.sharedPreferences, "corrects", 0)
        val incorrects = IntCache.Base(core.sharedPreferences, "incorrects", 0)

        return GameOverViewModel(
            core.clearViewModel,
            StatsRepository.Base(
                corrects,
                incorrects,
            )
        )
    }
}

class ProvideGameOverViewModel(
    core: Core,
    next: ProvideViewModel
) : AbstractProvideViewModel(core, next, GameOverViewModel::class.java) {

    override fun module(): Module<*> = GameOverModule(core)
}