package itis.ru.insultgenerator.model

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import itis.ru.insultgenerator.App
import itis.ru.insultgenerator.InsultGeneratorApi
import itis.ru.insultgenerator.database.AppDatabase
import itis.ru.insultgenerator.database.InsultDataDao
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG: String = "InsultInteractor"

class InsultInteractor {
    private val service: InsultGeneratorApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(InsultGeneratorApi::class.java)
    }

    private val insultDataDao: InsultDataDao? by lazy {
        App.context?.let { AppDatabase.getInstance(it)?.insultDataDao() }
    }

    fun getInsultList(): Single<MutableList<Insult>> =
        Observable.fromIterable(ITERABLE_ARRAY)
            .flatMap {
                service.getInsult()
            }
            .concatMap {
                Observable.just(it)
            }
            .toList()
            .map {
                insultDataDao?.nukeTable()
                it
            }
            .map {
                insultDataDao?.insert(it)
                it
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getInsultListFromDb(): Single<MutableList<Insult>>? {
        return insultDataDao?.getAll()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

    companion object {
        private const val BASE_URL: String = "https://evilinsult.com/"
        private val ITERABLE_ARRAY = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    }
}
