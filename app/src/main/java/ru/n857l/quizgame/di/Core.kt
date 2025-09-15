package ru.n857l.quizgame.di

import android.content.Context
import com.google.gson.Gson

class Core(
    context: Context,
    val clearViewModel: ClearViewModel
) {

    val sharedPreferences = context.getSharedPreferences("quizAppData", Context.MODE_PRIVATE)
    val gson = Gson()
}