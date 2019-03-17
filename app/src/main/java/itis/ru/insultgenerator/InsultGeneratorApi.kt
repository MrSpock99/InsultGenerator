package itis.ru.insultgenerator

import io.reactivex.Observable
import itis.ru.insultgenerator.model.Insult
import retrofit2.http.GET

interface InsultGeneratorApi{
    @GET("generate_insult.php?lang=en&type=json")
    fun getInsult(): Observable<Insult>
}
