package ru.n857l.quizgame

import android.graphics.Color
import androidx.appcompat.widget.AppCompatButton
import java.io.Serializable

interface ChoiceUiState : Serializable {

    fun update(button: AppCompatButton)

    abstract class Abstract(
        private val value: String,
        private val color: String,
        private val clickable: Boolean = false,
        private val enabled: Boolean = true
    ) : ChoiceUiState {
        override fun update(button: AppCompatButton) = with(button) {
            text = value
            isEnabled = enabled
            isClickable = clickable
            setBackgroundColor(Color.parseColor(color))
        }
    }

    data class NotAvailableToChoose(private val text: String) : Abstract(text, "#75797E", enabled = false)
    data class AvailableToChoose(private val text: String) : Abstract(text, "#5367B7", true)
    data class Correct(private val text: String) : Abstract(text, "#13CC2E")
    data class InCorrect(private val text: String) : Abstract(text, "#DD3A3D")

}