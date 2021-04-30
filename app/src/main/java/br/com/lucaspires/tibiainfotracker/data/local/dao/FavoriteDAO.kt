package br.com.lucaspires.tibiainfotracker.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.OnConflictStrategy
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.lucaspires.tibiainfotracker.data.local.entity.FavoriteEntity
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface FavoriteDAO {
    @Query("SELECT * FROM FavoriteEntity")
    fun getAll(): LiveData<List<FavoriteEntity>>

    @Query("SELECT * FROM FavoriteEntity WHERE characterName =:name")
    fun getCharacter(name: String): Maybe<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(char: FavoriteEntity): Completable

    @Delete
    fun deleteCharacter(char: FavoriteEntity): Completable
}