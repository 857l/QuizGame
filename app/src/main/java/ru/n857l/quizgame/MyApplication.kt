package ru.n857l.quizgame

import android.app.Application
import androidx.lifecycle.ViewModel

class MyApplication : Application() {

    lateinit var viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = GameViewModel(GameRepository())
    }
}