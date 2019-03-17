package itis.ru.insultgenerator.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import itis.ru.insultgenerator.view.InsultView

@InjectViewState
class InsultActivityPresenter : MvpPresenter<InsultView>() {

    fun setInsultTv(insult: String) {
        viewState.updateInsultTextView(insult)
    }
}
