package itis.ru.insultgenerator.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import itis.ru.insultgenerator.model.Insult

private const val DB_NAME: String = "INSULT.db"

@Database(entities = [Insult::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun insultDataDao(): InsultDataDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
