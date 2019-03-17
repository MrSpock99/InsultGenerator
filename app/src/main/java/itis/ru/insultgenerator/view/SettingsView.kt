package itis.ru.insultgenerator.view

import com.arellomobile.mvp.MvpView

interface SettingsView : MvpView {
    fun showPaginationDialog(paginationSize: Int)
}
