package ru.n857l.quizgame.core

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ru.n857l.quizgame.R
import ru.n857l.quizgame.game.GameScreen
import ru.n857l.quizgame.game.NavigateToGame
import ru.n857l.quizgame.stats.GameOverScreen
import ru.n857l.quizgame.stats.NavigateToGameOver
import ru.n857l.quizgame.views.Screen

class MainActivity : AppCompatActivity(), Navigate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            navigateToGame()
    }

    override fun navigate(screen: Screen) = screen.show(R.id.container, supportFragmentManager)
}

interface Navigate : NavigateToGame, NavigateToGameOver {

    fun navigate(screen: Screen)

    override fun navigateToGameOver() = navigate(GameOverScreen)

    override fun navigateToGame() = navigate(GameScreen)
}