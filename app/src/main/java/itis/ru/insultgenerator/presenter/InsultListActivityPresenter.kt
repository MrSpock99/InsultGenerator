package itis.ru.insultgenerator.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.rxkotlin.subscribeBy
import itis.ru.insultgenerator.model.Insult
import itis.ru.insultgenerator.model.InsultInteractor
import itis.ru.insultgenerator.model.SettingsInteractor
import itis.ru.insultgenerator.view.InsultListView

@InjectViewState
class InsultListActivityPresenter(
    private val insultInteractor: InsultInteractor,
    private val settingsInteractor: SettingsInteractor
) : MvpPresenter<InsultListView>() {

    fun updateInsultList() = insultInteractor
        .getInsultList()
        .doOnSubscribe { viewState.showProgress() }
        .doAfterTerminate { viewState.hideProgress() }
        .subscribeBy(onSuccess = {
            Log.d("MYLOG", "presenter suc " + it.toString())
            viewState.updateListView(it)
        }, onError = {
            Log.d("MYLOG", "presen error " + it.toString())
            updateInsultListFromCache()
        })

    fun updateInsultListFromCache() = insultInteractor
        .getInsultListFromDb()
        ?.doOnSubscribe {
            Log.d("MYLOG", "present subs " + it.toString())
            viewState.showProgress()
        }
        ?.doAfterTerminate { viewState.hideProgress() }
        ?.subscribeBy(onSuccess = {
            viewState.updateListView(it)
            Log.d("MYLOG", it.toString())
        }, onError = {
            Log.d("MYLOG", it.toString())
        })

    fun onInsultClick(insult: Insult) = viewState.navigateToDetails(insult)

    fun loadNextElements(i: Int) = insultInteractor
        .getInsultList()
        .doOnSubscribe { viewState.showProgress() }
        .doAfterTerminate { viewState.hideProgress() }
        .subscribeBy(onSuccess = {
            Log.d("MYLOG", "presenter suc " + it.toString())
            viewState.addItemsToListView(it)
        }, onError = {
            Log.d("MYLOG", "presen error " + it.toString())
        })

    fun getPaginationSize(): Int = settingsInteractor.getPaginationSize() ?: 0

    fun onMenuItemClick() = viewState.navigateToSettings()
}
