package ru.n857l.quizgame

data class QuestionAndChoices(
    val question: String,
    val choices: List<String>,
    val correctIndex: Int
)