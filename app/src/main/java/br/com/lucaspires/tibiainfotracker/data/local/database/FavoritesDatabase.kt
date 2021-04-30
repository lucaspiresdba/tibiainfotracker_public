package br.com.lucaspires.tibiainfotracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.lucaspires.tibiainfotracker.data.local.dao.FavoriteDAO
import br.com.lucaspires.tibiainfotracker.data.local.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDAO
}