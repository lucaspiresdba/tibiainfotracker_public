package br.com.lucaspires.tibiainfotracker.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "characterName") val name: String
)
