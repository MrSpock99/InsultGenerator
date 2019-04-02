package itis.ru.insultgenerator.di.module

import android.content.Context
import android.support.annotation.Nullable
import dagger.Module
import dagger.Provides
import itis.ru.insultgenerator.database.AppDatabase
import itis.ru.insultgenerator.database.InsultDataDao

@Module
class DatabaseModule(private val context: Context) {
    @Provides
    @Nullable
    fun provideDb():InsultDataDao? = AppDatabase.getInstance(context)?.insultDataDao()
}