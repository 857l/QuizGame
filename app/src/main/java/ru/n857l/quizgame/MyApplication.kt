package ru.n857l.quizgame

import android.app.Application

class MyApplication : Application() {

    lateinit var viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()

        val sharedPreferences = getSharedPreferences("QuizAppData", MODE_PRIVATE)

        viewModel = GameViewModel(
            GameRepository.Base(
                IntCache.Base(sharedPreferences, "index", 0),
                IntCache.Base(sharedPreferences, "userChoiceIndex", -1)
            )
        )
    }
}