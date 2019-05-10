package itis.ru.insultgenerator.database

import androidx.room.*
import io.reactivex.Single
import itis.ru.insultgenerator.model.Insult
import kotlinx.coroutines.Deferred

@Dao
interface InsultDataDao {
    @Query("SELECT * FROM insult")
    fun getAllAsync(): Deferred<MutableList<Insult>>

    @Query("SELECT * FROM insult WHERE id = :id")
    fun getByIdAsync(id: Int): Deferred<Insult>

    @Query("DELETE FROM insult")
    fun nukeTable()

    @Insert
    fun insert(insult: Insult)

    @Insert
    fun insert(insults: List<Insult>)

    @Update
    fun update(insults: Insult)

    @Delete
    fun delete(insult: Insult)
}
