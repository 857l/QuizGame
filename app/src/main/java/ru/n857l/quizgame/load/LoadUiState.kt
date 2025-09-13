package ru.n857l.quizgame.load

import ru.n857l.quizgame.R
import ru.n857l.quizgame.game.NavigateToGame
import ru.n857l.quizgame.views.error.ErrorUiState
import ru.n857l.quizgame.views.error.UpdateError
import ru.n857l.quizgame.views.visibilityButton.UpdateVisibility
import ru.n857l.quizgame.views.visibilityButton.VisibilityUiState

interface LoadUiState {

    fun show(
        errorTextView: UpdateError,
        retryButton: UpdateVisibility,
        progressBar: UpdateVisibility
    )

    fun navigate(navigateToGame: NavigateToGame) = Unit

    abstract class Abstract(
        private val errorUiState: ErrorUiState,
        private val retryUiState: VisibilityUiState,
        private val progressUiState: VisibilityUiState
    ) : LoadUiState {
        override fun show(
            errorTextView: UpdateError,
            retryButton: UpdateVisibility,
            progressBar: UpdateVisibility
        ) {
            errorTextView.update(errorUiState)
            retryButton.update(retryUiState)
            progressBar.update(progressUiState)
        }
    }

    object Progress : Abstract(ErrorUiState.Hide, VisibilityUiState.Gone, VisibilityUiState.Visible)

    object Success : Abstract(ErrorUiState.Hide, VisibilityUiState.Gone, VisibilityUiState.Gone) {

        override fun navigate(navigateToGame: NavigateToGame) = navigateToGame.navigateToGame()
    }

    data class Error(private val message: String) :
        Abstract(
            ErrorUiState.Show(R.string.no_internet_connection),
            VisibilityUiState.Visible,
            VisibilityUiState.Gone,
        )
}