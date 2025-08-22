package ru.n857l.quizgame.stats

import ru.n857l.quizgame.views.stats.StatsUiState

class GameOverViewModel(private val repository: StatsRepository) {

    fun statsUiState(): StatsUiState {
        val (corrects, incorrects) = repository.stats()
        return StatsUiState.Base(corrects, incorrects)
    }
}