package itis.ru.insultgenerator.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import itis.ru.insultgenerator.model.InsultInteractor
import itis.ru.insultgenerator.model.SettingsInteractor
import itis.ru.insultgenerator.presenter.InsultActivityPresenter
import itis.ru.insultgenerator.presenter.InsultListActivityPresenter
import itis.ru.insultgenerator.presenter.SettingsActivityPresenter
import ru.terrakok.cicerone.Router

@Module
class PresenterModule(private val context: Context) {
    @Provides
    fun provideInsultInteractor(): InsultInteractor = InsultInteractor(context)

    @Provides
    fun provideSettingsInteractor(): SettingsInteractor = SettingsInteractor(context)

    @Provides
    fun provideInsultListActivityPresenter(
            insultInteractor: InsultInteractor,
            settingsInteractor: SettingsInteractor,
            router: Router
    ): InsultListActivityPresenter = InsultListActivityPresenter(insultInteractor, settingsInteractor, router)

    @Provides
    fun provideInsultActivityPresenter(router: Router): InsultActivityPresenter = InsultActivityPresenter(router)

    @Provides
    fun provideSettingsActivityPresenter(settingsInteractor: SettingsInteractor): SettingsActivityPresenter =
            SettingsActivityPresenter(settingsInteractor)
}
