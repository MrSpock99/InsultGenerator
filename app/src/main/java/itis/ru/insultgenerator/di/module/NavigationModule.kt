package itis.ru.insultgenerator.di.module

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class NavigationModule {
    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun provideRouter(): Router = cicerone.router

    @Provides
    fun provideNavigationHolder(): NavigatorHolder = cicerone.navigatorHolder
}