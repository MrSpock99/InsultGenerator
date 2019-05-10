package itis.ru.insultgenerator

import itis.ru.insultgenerator.model.Insult
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface InsultGeneratorApi {
    @GET("generate_insult.php?lang=en&type=json")
    fun getInsultAsync(): Deferred<Insult>
}
