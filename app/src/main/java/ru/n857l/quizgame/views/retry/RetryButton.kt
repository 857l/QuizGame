package ru.n857l.quizgame.views.retry

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import ru.n857l.quizgame.views.visibilityButton.UpdateVisibility
import ru.n857l.quizgame.views.visibilityButton.VisibilitySavedState
import ru.n857l.quizgame.views.visibilityButton.VisibilityUiState

class RetryButton : AppCompatButton, UpdateVisibility {

    private lateinit var state: VisibilityUiState

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = VisibilitySavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as VisibilitySavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }

    override fun update(visibility: Int) {
        this.visibility = visibility
    }

    override fun update(state: VisibilityUiState) {
        this.state = state
        this.state.update(this)
    }
}