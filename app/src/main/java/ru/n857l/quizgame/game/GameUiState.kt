package ru.n857l.quizgame.game

import ru.n857l.quizgame.stats.NavigateToGameOver
import ru.n857l.quizgame.views.choice.ChoiceUiState
import ru.n857l.quizgame.views.choice.UpdateChoiceButton
import ru.n857l.quizgame.views.question.UpdateText
import ru.n857l.quizgame.views.visibilityButton.UpdateVisibility
import ru.n857l.quizgame.views.visibilityButton.VisibilityUiState
import java.io.Serializable

interface GameUiState : Serializable {

    fun update(
        questionTextView: UpdateText,
        firstChoiceButton: UpdateChoiceButton,
        secondChoiceButton: UpdateChoiceButton,
        thirdChoiceButton: UpdateChoiceButton,
        fourthChoiceButton: UpdateChoiceButton,
        nextButton: UpdateVisibility,
        checkButton: UpdateVisibility
    ) = Unit

    fun navigate(navigate: NavigateToGameOver) = Unit

    object Empty : GameUiState

    object Finish : GameUiState {

        override fun navigate(navigate: NavigateToGameOver) = navigate.navigateToGameOver()
    }

    data class AskedQuestion(
        private val question: String,
        private val choices: List<String>
    ) : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            fourthChoiceButton: UpdateChoiceButton,
            nextButton: UpdateVisibility,
            checkButton: UpdateVisibility
        ) {
            questionTextView.update(question)
            firstChoiceButton.updateState(ChoiceUiState.Initial(choices[0]))
            secondChoiceButton.updateState(ChoiceUiState.Initial(choices[1]))
            thirdChoiceButton.updateState(ChoiceUiState.Initial(choices[2]))
            fourthChoiceButton.updateState(ChoiceUiState.Initial(choices[3]))
            nextButton.update(VisibilityUiState.Gone)
            checkButton.update(VisibilityUiState.Gone)
        }
    }

    data class  ChoiceMade(
        private val choices: List<ChoiceUiState>
    ) : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            fourthChoiceButton: UpdateChoiceButton,
            nextButton: UpdateVisibility,
            checkButton: UpdateVisibility
        ) {
            firstChoiceButton.updateState(choices[0])
            secondChoiceButton.updateState(choices[1])
            thirdChoiceButton.updateState(choices[2])
            fourthChoiceButton.updateState(choices[3])
            checkButton.update(VisibilityUiState.Visible)
        }
    }

    data class AnswerChecked(
        private val choices: List<ChoiceUiState>
    ) : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            fourthChoiceButton: UpdateChoiceButton,
            nextButton: UpdateVisibility,
            checkButton: UpdateVisibility
        ) {
            firstChoiceButton.updateState(choices[0])
            secondChoiceButton.updateState(choices[1])
            thirdChoiceButton.updateState(choices[2])
            fourthChoiceButton.updateState(choices[3])
            checkButton.update(VisibilityUiState.Gone)
            nextButton.update(VisibilityUiState.Visible)
        }
    }

}