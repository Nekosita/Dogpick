package com.example.dogpick.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dogpick.data.dogData

@Database(entities = [dogData::class], version = 1, exportSchema = false)
abstract class dogDataBase :RoomDatabase() {

    //データベースをアクセスする用の関数
    abstract fun dogDataDao(): dogDataDao

    companion object {
        fun create(context: Context): dogDataBase {
            return Room.databaseBuilder(
                context,
                dogDataBase::class.java,
                "dog_database"
            ).fallbackToDestructiveMigration()
                .build()
        }
    }

}