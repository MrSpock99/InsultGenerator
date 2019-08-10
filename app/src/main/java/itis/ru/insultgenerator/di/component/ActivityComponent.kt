package itis.ru.insultgenerator.di.component

import dagger.Component
import itis.ru.insultgenerator.di.module.*
import itis.ru.insultgenerator.view.GetInsultActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, NetModule::class, DatabaseModule::class, ViewModelFactoryModule::class,
        ViewModelModule::class]
)
interface ActivityComponent {
    fun inject(getInsultActivity: GetInsultActivity)
}
