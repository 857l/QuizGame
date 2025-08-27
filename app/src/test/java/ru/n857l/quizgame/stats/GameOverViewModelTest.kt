package ru.n857l.quizgame.stats

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.n857l.quizgame.views.stats.StatsUiState

class GameOverViewModelTest {

    @Test
    fun test() {
        val repository = FakeRepository()
        val viewModel = GameOverViewModel(repository = repository)

        assertEquals(StatsUiState.Base(2, 3), viewModel.init(isFirstRun = true))
        assertEquals(1, repository.clearCalledCount)

        assertEquals(StatsUiState.Empty, viewModel.init(isFirstRun = false))
        assertEquals(1, repository.clearCalledCount)
    }
}

private class FakeRepository : StatsRepository {

    override fun stats(): Pair<Int, Int> = Pair(2, 3)

    var clearCalledCount = 0

    override fun clear() {
        clearCalledCount++
    }
}