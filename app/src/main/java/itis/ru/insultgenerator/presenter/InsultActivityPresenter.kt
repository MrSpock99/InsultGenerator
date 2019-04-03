package itis.ru.insultgenerator.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import itis.ru.insultgenerator.view.InsultView
import ru.terrakok.cicerone.Router

@InjectViewState
class InsultActivityPresenter(private val router: Router) : MvpPresenter<InsultView>() {

    fun setInsultTv(insult: String) {
        viewState.updateInsultTextView(insult)
    }

    fun onBackPressed() {
        router.exit()
    }
}
