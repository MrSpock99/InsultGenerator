package itis.ru.insultgenerator.di.component

import dagger.Component
import itis.ru.insultgenerator.di.module.DatabaseModule
import itis.ru.insultgenerator.di.module.NetModule
import itis.ru.insultgenerator.model.InsultInteractor

@Component(modules = [NetModule::class, DatabaseModule::class])
interface DataComponent {
    fun inject(interactor: InsultInteractor)
}
