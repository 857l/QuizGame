package ru.n857l.quizgame

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import ru.n857l.quizgame.game.CorrectAndUserChoiceIndexes
import ru.n857l.quizgame.game.GameRepository
import ru.n857l.quizgame.game.GameUiState
import ru.n857l.quizgame.game.GameViewModel
import ru.n857l.quizgame.game.QuestionAndChoices
import ru.n857l.quizgame.views.choice.ChoiceUiState

class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @Before
    fun setup() {
        viewModel = GameViewModel(repository = FakeRepository())
    }

    /**
    QGTC-01
     */

    @Test
    fun caseNumber1() {
        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.AskedQuestion(
            question = "q1",
            choices = listOf<String>("c1", "c2", "c3", "c4")
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseFirst()
        expected = GameUiState.ChoiceMade(
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.check()
        expected = GameUiState.AnswerChecked(
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.Correct,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun caseNumber2() {
        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.AskedQuestion(
            question = "q1",
            choices = listOf<String>("c1", "c2", "c3", "c4")
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseFirst()
        expected = GameUiState.ChoiceMade(
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseSecond()
        expected = GameUiState.ChoiceMade(
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseThird()
        expected = GameUiState.ChoiceMade(
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseForth()
        expected = GameUiState.ChoiceMade(
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.check()
        expected = GameUiState.AnswerChecked(
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.Correct,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.Incorrect,
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = GameUiState.AskedQuestion(
            question = "q2",
            choices = listOf<String>("cd1", "cd2", "cd3", "cd4")
        )
        assertEquals(expected, actual)

    }
}

private class FakeRepository : GameRepository {

    private val list: List<QuestionAndChoices> = listOf(
        QuestionAndChoices(
            question = "q1",
            choices = listOf("c1", "c2", "c3", "c4"),
            correctIndex = 0
        ),
        QuestionAndChoices(
            question = "q2",
            choices = listOf("cd1", "cd2", "cd3", "cd4"),
            correctIndex = 0
        )
    )

    private var index = 0

    override fun questionAndChoices(): QuestionAndChoices {
        return list[index]
    }

    private var userChoiceIndex = -1

    override fun saveUserChoice(index: Int) {
        userChoiceIndex = index
    }

    override fun check(): CorrectAndUserChoiceIndexes {
        return CorrectAndUserChoiceIndexes(
            correctIndex = questionAndChoices().correctIndex,
            userChoiceIndex = userChoiceIndex
        )
    }

    override fun next() {
        userChoiceIndex = -1
        index++
        if (index == list.size)
            index = 0
    }
}