package com.example.dogpick.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//データベースの表を定義します
@Entity(tableName = "dog_table")
data class dogData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val message: String,
    val status: String
)