package itis.ru.insultgenerator.model

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import itis.ru.insultgenerator.InsultGeneratorApi
import itis.ru.insultgenerator.database.InsultDataDao
import javax.inject.Inject

private const val TAG: String = "InsultInteractor"

class InsultInteractor @Inject constructor(
    private val context: Context,
    var service: InsultGeneratorApi,
    var insultDataDao: InsultDataDao
) {
    fun getInsult(): Observable<Insult> = service.getInsult()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

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
                insultDataDao.nukeTable()
                it
            }
            .map {
                insultDataDao.insert(it)
                it
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getInsultListFromDb(): Single<MutableList<Insult>>? {
        return insultDataDao.getAll().subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

    companion object {
        private val ITERABLE_ARRAY = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    }
}
