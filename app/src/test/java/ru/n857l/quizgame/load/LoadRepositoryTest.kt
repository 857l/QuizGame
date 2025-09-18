package ru.n857l.quizgame.load

import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import ru.n857l.quizgame.load.data.QuestionAndChoicesCloud
import java.net.HttpURLConnection
import java.net.URL

class LoadRepositoryTest {

    private val gson = Gson()

    @Test
    fun test() {
        val url = "https://opentdb.com/api.php?amount=10&type=multiple"
        val connection = URL(url).openConnection() as HttpURLConnection
        try {
            val data: String = connection.inputStream.bufferedReader().use { it.readText() }
            assertTrue(data.isNotEmpty())

            val response = gson.fromJson(data, Response::class.java)
            val list = response.results
            assertEquals(10, list.size)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection.disconnect()
        }
    }
}

private class Response(
    val results: List<QuestionAndChoicesCloud>
)

private data class QuestionAndChoicesCloud(
    private val question: String,
    private val correct_answer: String,
    private val incorrect_answers: List<String>
)