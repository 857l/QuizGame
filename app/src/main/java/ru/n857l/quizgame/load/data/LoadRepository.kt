package ru.n857l.quizgame.load.data

import com.google.gson.Gson
import ru.n857l.quizgame.core.StringCache

interface LoadRepository {

    suspend fun load(): LoadResult

    class Base(
        private val service: QuizService,
        private val parseQuestionAndChoices: ParseQuestionAndChoices,
        private val dataCache: StringCache,
    ) : LoadRepository {

        override suspend fun load(): LoadResult {
            try {
                val result = service.questionAndChoices().execute()
                if (result.isSuccessful) {
                    val body = result.body()!!
                    if (body.responseCode == 0) {
                        val list = body.dataList
                        if (list.isEmpty()) {
                            return (LoadResult.Error("Empty data, try again later"))
                        } else {
                            val data = parseQuestionAndChoices.toString(body)
                            dataCache.save(data)
                            return (LoadResult.Success)
                        }
                    } else {
                        return (LoadResult.Error(handleResponseCode(result.body()!!.responseCode)))
                    }
                } else {
                    return (LoadResult.Error(handleResponseCode(result.body()!!.responseCode)))
                }
            } catch (e: Exception) {
                return (LoadResult.Error(e.message ?: "error"))
            }
        }

        private fun handleResponseCode(code: Int): String {
            return when (code) {
                1 -> "No Results Could not return results. The API doesn't have enough questions for your query. (Ex. Asking for 50 Questions in a Category that only has 20.)\n"
                2 -> "Invalid Parameter Contains an invalid parameter. Arguements passed in aren't valid. (Ex. Amount = Five)"
                3 -> "Token Not Found Session Token does not exist."
                4 -> "Token Empty Session Token has returned all possible questions for the specified query. Resetting the Token is necessary."
                5 -> "Rate Limit Too many requests have occurred. Each IP can only access the API once every 5 seconds."
                else -> ""
            }
        }
    }
}

interface ParseQuestionAndChoices {

    fun toString(data: Any): String

    fun parse(source: String): QuizResponse

    class Base(
        private val gson: Gson
    ) : ParseQuestionAndChoices {

        override fun toString(data: Any): String {
            return gson.toJson(data)
        }

        override fun parse(source: String): QuizResponse {
            return gson.fromJson(source, QuizResponse::class.java)
        }
    }
}