package itis.ru.insultgenerator.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import itis.ru.insultgenerator.model.Insult

@StateStrategyType(value = SingleStateStrategy::class)
interface InsultListView : MvpView {
    fun updateListView(list: MutableList<Insult>)
    fun addItemsToListView(list: List<Insult>)
    fun showProgress()
    fun hideProgress()
    fun navigateToDetails(insult: Insult)
    fun navigateToSettings()
}
