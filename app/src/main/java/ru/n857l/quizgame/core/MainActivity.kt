package ru.n857l.quizgame.core

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ru.n857l.quizgame.MyViewModel
import ru.n857l.quizgame.NavigateToLoad
import ru.n857l.quizgame.R
import ru.n857l.quizgame.di.ProvideViewModel
import ru.n857l.quizgame.game.GameScreen
import ru.n857l.quizgame.game.NavigateToGame
import ru.n857l.quizgame.load.LoadScreen
import ru.n857l.quizgame.stats.GameOverScreen
import ru.n857l.quizgame.stats.NavigateToGameOver

class MainActivity : AppCompatActivity(), Navigate, ProvideViewModel {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            navigateToGame()
    }

    override fun navigate(screen: Screen) = screen.show(R.id.container, supportFragmentManager)

    override fun <T : MyViewModel> makeViewModel(clasz: Class<T>): T =
        (application as ProvideViewModel).makeViewModel(clasz)
}

interface Navigate : NavigateToGame, NavigateToGameOver, NavigateToLoad {

    fun navigate(screen: Screen)

    override fun navigateToGameOver() = navigate(GameOverScreen)

    override fun navigateToGame() = navigate(GameScreen)

    override fun navigateToLoad() = navigate(LoadScreen)
}