package ru.n857l.quizgame.views.choice

import java.io.Serializable

interface ChoiceUiState : Serializable {

    fun update(update: UpdateChoiceButton)

    abstract class Abstract(
        private val color: String,
        private val clickable: Boolean = false,
        private val enabled: Boolean = true
    ) : ChoiceUiState {
        override fun update(update: UpdateChoiceButton) {
            update.update(color, clickable, enabled)
        }
    }


    object NotAvailableToChoose : Abstract("#75797E", enabled = false)

    data class Initial(private val text: String) : Abstract("#5367B7", true) {

        override fun update(update: UpdateChoiceButton) {
            super.update(update)
            update.update(text)
        }
    }

    object AvailableToChoose : Abstract("#5367B7", true)
    object Correct : Abstract("#13CC2E")
    object Incorrect : Abstract("#DD3A3D")

}