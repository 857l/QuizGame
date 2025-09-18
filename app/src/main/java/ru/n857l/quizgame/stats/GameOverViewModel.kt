package ru.n857l.quizgame.stats

import ru.n857l.quizgame.core.MyViewModel
import ru.n857l.quizgame.di.ClearViewModel
import ru.n857l.quizgame.views.stats.StatsUiState

class GameOverViewModel(
    private val clearViewModel: ClearViewModel,
    private val repository: StatsRepository
) : MyViewModel {

    fun init(isFirstRun: Boolean): StatsUiState {
        return if (isFirstRun) {
            val (corrects, incorrects) = repository.stats()
            repository.clear()
            StatsUiState.Base(corrects, incorrects)
        } else {
            StatsUiState.Empty
        }
    }

    fun clear() {
        clearViewModel.clear(GameOverViewModel::class.java)
    }
}