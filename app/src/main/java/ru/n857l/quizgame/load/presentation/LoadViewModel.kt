package ru.n857l.quizgame.load.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.n857l.quizgame.core.MyViewModel
import ru.n857l.quizgame.load.data.RunAsync
import ru.n857l.quizgame.load.data.LoadRepository

class LoadViewModel(
    private val repository: LoadRepository,
    private val observable: UiObservable,
    private val runAsync: RunAsync
) : MyViewModel {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun load(isFirstRun: Boolean = true) {
        if (isFirstRun) {
            observable.postUiState(LoadUiState.Progress)
            runAsync.handleAsync(viewModelScope, {
                val result = repository.load()
                if (result.isSuccessful())
                    LoadUiState.Success
                else
                    LoadUiState.Error(result.message())
            }) {
                observable.postUiState(it)
            }
        }
    }

    fun startUpdates(observer: (LoadUiState) -> Unit) = observable.register(observer)

    fun stopUpdates() = observable.unregister()
}