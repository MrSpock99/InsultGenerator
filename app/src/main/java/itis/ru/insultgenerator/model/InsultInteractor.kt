package itis.ru.insultgenerator.model

import android.content.Context
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import itis.ru.insultgenerator.InsultGeneratorApi
import itis.ru.insultgenerator.database.InsultDataDao
import itis.ru.insultgenerator.di.module.DatabaseModule
import itis.ru.insultgenerator.di.module.NetModule
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.annotations.Nullable
import java.net.UnknownHostException
import javax.inject.Inject

private const val TAG: String = "InsultInteractor"

class InsultInteractor(private val context: Context) {
    @Inject
    lateinit var service: InsultGeneratorApi

    @Inject
    @Nullable
    lateinit var insultDataDao: InsultDataDao

    init {
        injectDependency()
    }

    private fun injectDependency() {
        val netComponent = DaggerDataComponent.builder()
            .netModule(NetModule())
            .databaseModule(DatabaseModule(context))
            .build()
        netComponent.inject(this)
    }

    suspend fun getInsultListAsync(): MutableList<Insult> = withContext(Dispatchers.IO) {
        val list: MutableList<Insult> = mutableListOf()

        try {
            for (i in 0 until 10) {
                list.add(service.getInsultAsync().await())
            }
        } catch (ex: UnknownHostException) {
            ex.printStackTrace()
            return@withContext insultDataDao.getAllAsync().await()
        }

        insultDataDao.insert(list)
        return@withContext list
    }

    suspend fun getInsultListFromDb(): MutableList<Insult> = withContext(Dispatchers.IO){
        return@withContext insultDataDao.getAllAsync().await()
    }
}
/*Observable.fromIterable(ITERABLE_ARRAY)
    .flatMap {
        service.getInsultAsync()
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
    .observeOn(AndroidSchedulers.mainThread())*/

/*
fun getInsultListFromDb(): Single<MutableList<Insult>>? {
    return insultDataDao.getAll().subscribeOn(Schedulers.io())
        ?.observeOn(AndroidSchedulers.mainThread())
}
*/

