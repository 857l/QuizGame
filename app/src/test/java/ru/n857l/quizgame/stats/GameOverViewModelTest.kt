package ru.n857l.quizgame.stats

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.n857l.quizgame.views.stats.StatsUiState

class GameOverViewModelTest {

    @Test
    fun test() {
        val repository = FakeRepository()
        val viewModel = GameOverViewModel(repository = repository)

        assertEquals(StatsUiState.Base(2, 3), viewModel.statsUiState())
    }
}

private class FakeRepository : StatsRepository {

    override fun stats(): Pair<Int, Int> = Pair(2, 3)
}