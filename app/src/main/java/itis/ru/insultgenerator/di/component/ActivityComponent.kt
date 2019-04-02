package itis.ru.insultgenerator.di.component

import dagger.Component
import itis.ru.insultgenerator.di.module.PresenterModule
import itis.ru.insultgenerator.view.InsultActivity
import itis.ru.insultgenerator.view.InsultListActivity
import itis.ru.insultgenerator.view.SettingsActivity

@Component(modules = [PresenterModule::class])
interface ActivityComponent {
    fun inject(insultActivity: InsultActivity)
    fun inject(insultListActivity: InsultListActivity)
    fun inject(settingsActivity: SettingsActivity)
}
