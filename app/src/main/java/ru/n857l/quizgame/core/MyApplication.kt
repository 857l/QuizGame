package ru.n857l.quizgame.core

import android.app.Application
import ru.n857l.quizgame.game.GameRepository
import ru.n857l.quizgame.game.GameViewModel

class MyApplication : Application() {

    lateinit var gameViewModel: GameViewModel
    lateinit var gameOverViewModel: GameOverViewModel

    override fun onCreate() {
        super.onCreate()

        val sharedPreferences = getSharedPreferences("QuizAppData", MODE_PRIVATE)

        gameViewModel = GameViewModel(
            GameRepository.Base(
                IntCache.Base(sharedPreferences, "index", 0),
                IntCache.Base(sharedPreferences, "userChoiceIndex", -1)
            )
        )

        gameOverViewModel = GameOverViewModel()//todo
    }
}