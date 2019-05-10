package itis.ru.insultgenerator.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import itis.ru.insultgenerator.model.Insult
import itis.ru.insultgenerator.model.InsultInteractor
import itis.ru.insultgenerator.model.SettingsInteractor
import itis.ru.insultgenerator.utils.Screens
import itis.ru.insultgenerator.view.InsultListView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router

@InjectViewState
class InsultListActivityPresenter(
    private val insultInteractor: InsultInteractor,
    private val settingsInteractor: SettingsInteractor,
    private val router: Router
) : MvpPresenter<InsultListView>() {

    fun updateInsultList() {
        GlobalScope.launch {
            viewState.updateListView(insultInteractor.getInsultListAsync())
        }

        }/*insultInteractor
        .getInsultListAsync()
        .doOnSubscribe { viewState.showProgress() }
        .doAfterTerminate { viewState.hideProgress() }
        .subscribeBy(onSuccess = {
            Log.d("MYLOG", "presenter suc " + it.toString())
            viewState.updateListView(it)
        }, onError = {
            Log.d("MYLOG", "presen error " + it.toString())
            updateInsultListFromCache()
        })*/

    fun updateInsultListFromCache(){} /*= insultInteractor
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
        })*/

    fun onInsultClick(insult: Insult) = router.navigateTo(Screens.InsultScreen(insult.insult ?: ""))

    fun loadNextElements(i: Int) = {

    }/*insultInteractor
        .getInsultListAsync()
        .doOnSubscribe { viewState.showProgress() }
        .doAfterTerminate { viewState.hideProgress() }
        .subscribeBy(onSuccess = {
            Log.d("MYLOG", "presenter suc " + it.toString())
            viewState.addItemsToListView(it)
        }, onError = {
            Log.d("MYLOG", "presen error " + it.toString())
        })*/

    fun getPaginationSize(): Int = settingsInteractor.getPaginationSize() ?: 0

    fun onMenuItemClick() = router.navigateTo(Screens.SettingsScreen())
}
