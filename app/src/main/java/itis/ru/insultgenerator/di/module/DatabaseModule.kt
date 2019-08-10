package itis.ru.insultgenerator.di.module

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import itis.ru.insultgenerator.database.AppDatabase
import itis.ru.insultgenerator.database.DB_NAME
import itis.ru.insultgenerator.database.InsultDataDao
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDb(context: Context): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, DB_NAME
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideJokeDao(appDatabase: AppDatabase): InsultDataDao = appDatabase.insultDataDao()
}