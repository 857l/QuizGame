package ru.n857l.quizgame

interface GameRepository {

    fun questionAndChoices(): QuestionAndChoices

    fun saveUserChoice(index: Int)

    fun check(): CorrectAndUserChoiceIndexes

    fun next()

    class Base(
        private val list: List<QuestionAndChoices> = listOf(
            QuestionAndChoices(
                question = "What color is the sky?",
                choices = listOf("blue", "green", "red", "yellow"),
                correctIndex = 0
            ),
            QuestionAndChoices(
                question = "What color is the grass?",
                choices = listOf("green", "blue", "yellow", "red"),
                correctIndex = 0
            )
        )
    ) : GameRepository {

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
            if (index + 1 == list.size)
                index = 0
            else
                index++
        }
    }
}