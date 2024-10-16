package com.example.dogpick.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dogpick.data.dogData


//データベースアクセスする関数定義
@Dao
interface dogDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDogData(dogData: dogData)

    @Delete
    suspend fun deleteDogData(dogData: dogData)

    @Query("SELECT * FROM dog_table")
    fun getAllDogData(): LiveData<dogData>

    @Query("SELECT * FROM dog_table")
    fun getAllDogDataInTypeList():  LiveData<List<dogData>>


}