package ru.n857l.quizgame.load

import ru.n857l.quizgame.MyViewModel

class LoadViewModel(
    private val repository: LoadRepository,
    private val observable: UiObservable
) : MyViewModel {

    fun load(isFirstRun: Boolean = true) {
        if (isFirstRun) {
            observable.postUiState(LoadUiState.Progress)
            repository.load {
                observable.postUiState(
                    if (it.isSuccessful())
                        LoadUiState.Success
                    else
                        LoadUiState.Error(it.message())
                )
            }
        }
    }

    fun startUpdates(observer: (LoadUiState) -> Unit) = observable.register(observer)

    fun stopUpdates() = observable.unregister()
}