package ru.n857l.quizgame

import android.graphics.Color
import androidx.appcompat.widget.AppCompatButton

interface ChoiceUiState {

    fun update(button: AppCompatButton)

    abstract class Abstract(
        private val value: String,
        private val color: String,
        private val clickable: Boolean = false,
        private val enabled: Boolean = true
    ) : ChoiceUiState {
        override fun update(button: AppCompatButton) = with(button) {
            text = value
            if (enabled)
                setBackgroundColor(Color.parseColor(color))
            isEnabled = enabled
            isClickable = clickable
        }
    }

    data class NotAvailableToChoose(private val text: String) : Abstract(text, "", enabled = false)
    data class AvailableToChoose(private val text: String) : Abstract(text, "#5367B7", true)
    data class Correct(private val text: String) : Abstract(text, "#13CC2E")
    data class InCorrect(private val text: String) : Abstract(text, "#DD3A3D")

}