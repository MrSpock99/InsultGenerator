package itis.ru.insultgenerator.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import itis.ru.insultgenerator.model.Insult

const val DB_NAME: String = "INSULT.db"

@Database(entities = [Insult::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun insultDataDao(): InsultDataDao
}
