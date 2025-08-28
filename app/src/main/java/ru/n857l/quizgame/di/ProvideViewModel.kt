package ru.n857l.quizgame.di

import ru.n857l.quizgame.MyViewModel
import ru.n857l.quizgame.game.di.ProvideGameViewModel
import ru.n857l.quizgame.stats.di.ProvideGameOverViewModel

interface ProvideViewModel {

    fun <T : MyViewModel> makeViewModel(clasz: Class<T>): T

    class Make(core: Core) : ProvideViewModel {

        private var chain: ProvideViewModel

        init {
            chain = Error()
            chain = ProvideGameViewModel(core, chain)
            chain = ProvideGameOverViewModel(core, chain)
        }

        override fun <T : MyViewModel> makeViewModel(clasz: Class<T>): T =
            chain.makeViewModel(clasz)
    }

    class Error : ProvideViewModel {

        override fun <T : MyViewModel> makeViewModel(clasz: Class<T>): T {
            throw IllegalStateException("unknown class $clasz")
        }
    }
}