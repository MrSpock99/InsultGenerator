package itis.ru.insultgenerator.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import itis.ru.insultgenerator.model.SettingsInteractor
import itis.ru.insultgenerator.view.SettingsView

@InjectViewState
class SettingsActivityPresenter(private val settingsInteractor: SettingsInteractor) : MvpPresenter<SettingsView>() {

    fun setPaginationSize(size: Int) {
        settingsInteractor.savePaginationSize(size)
    }

    fun showPaginationDialog() {
        settingsInteractor.getPaginationSize()?.let { viewState.showPaginationDialog(it) }
    }
}
