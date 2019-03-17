package itis.ru.insultgenerator.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = SingleStateStrategy::class)
interface InsultView : MvpView {
    fun updateInsultTextView(text: String)
}
