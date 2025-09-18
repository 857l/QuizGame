package ru.n857l.quizgame.game.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.n857l.quizgame.load.data.RunAsync
import ru.n857l.quizgame.core.StringCache
import ru.n857l.quizgame.di.AbstractProvideViewModel
import ru.n857l.quizgame.di.Core
import ru.n857l.quizgame.di.Module
import ru.n857l.quizgame.di.ProvideViewModel
import ru.n857l.quizgame.load.data.LoadRepository
import ru.n857l.quizgame.load.presentation.LoadViewModel
import ru.n857l.quizgame.load.data.ParseQuestionAndChoices
import ru.n857l.quizgame.load.data.QuizResponse
import ru.n857l.quizgame.load.data.QuizService
import ru.n857l.quizgame.load.presentation.UiObservable
import java.util.concurrent.TimeUnit

class ProvideLoadViewModel(core: Core, next: ProvideViewModel) :
    AbstractProvideViewModel(core, next, LoadViewModel::class.java) {

    override fun module(): Module<*> = LoadModule(core)
}

class LoadModule(private val core: Core) : Module<LoadViewModel> {

    override fun viewModel(): LoadViewModel {
        val responseDefault = QuizResponse(-1, emptyList())
        val defaultResponse = core.gson.toJson(responseDefault)
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return LoadViewModel(
            LoadRepository.Base(
                retrofit.create(QuizService::class.java),
                ParseQuestionAndChoices.Base(core.gson),
                StringCache.Base(core.sharedPreferences, "response_data", defaultResponse)
            ),
            UiObservable.Base(),
            RunAsync.Base()
        )
    }
}