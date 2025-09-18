package ru.n857l.quizgame.game

import ru.n857l.quizgame.core.IntCache
import ru.n857l.quizgame.core.StringCache
import ru.n857l.quizgame.load.data.ParseQuestionAndChoices

interface GameRepository {

    fun questionAndChoices(): QuestionAndChoices

    fun saveUserChoice(index: Int)

    fun check(): CorrectAndUserChoiceIndexes

    fun next()

    fun isLastQuestion(): Boolean

    fun clear()

    class Base(
        private val corrects: IntCache,
        private val incorrects: IntCache,
        private val index: IntCache,
        private val userChoiceIndex: IntCache,
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

        constructor(
            corrects: IntCache,
            incorrects: IntCache,
            index: IntCache,
            userChoiceIndex: IntCache,
            dataCache: StringCache,
            parseQuestionAndChoices: ParseQuestionAndChoices
        ) : this(
            corrects,
            incorrects,
            index,
            userChoiceIndex,
            parseQuestionAndChoices.parse(dataCache.read()).dataList.map {
                val list = mutableListOf<String>()
                list.add(it.correctAnswer)
                list.addAll(it.incorrectAnswers)
                val finalList = list.shuffled()
                val indexOfCorrect = finalList.indexOf(it.correctAnswer)
                QuestionAndChoices(
                    it.question,
                    finalList,
                    indexOfCorrect
                )
            }
        )

        override fun questionAndChoices(): QuestionAndChoices {
            return list[index.read()]
        }

        override fun saveUserChoice(index: Int) {
            userChoiceIndex.save(index)
        }

        override fun check(): CorrectAndUserChoiceIndexes {
            val correctIndex = questionAndChoices().correctIndex

            if (userChoiceIndex.read() == correctIndex) {
                corrects.save(corrects.read() + 1)
            } else {
                incorrects.save(incorrects.read() + 1)
            }
            return CorrectAndUserChoiceIndexes(
                correctIndex = correctIndex,
                userChoiceIndex = userChoiceIndex.read()
            )
        }

        override fun next() {
            userChoiceIndex.save(-1)
            index.save(index.read() + 1)
        }

        override fun isLastQuestion() = index.read() == list.size

        override fun clear() {
            userChoiceIndex.save(-1)
            index.save(0)
        }
    }
}