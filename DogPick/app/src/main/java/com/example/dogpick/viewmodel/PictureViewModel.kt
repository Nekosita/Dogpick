package com.example.dogpick.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogpick.data.dogData
import com.example.dogpick.database.dogDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PictureViewModel(private val dogDataBase: dogDataBase): ViewModel() {


    //データベースの処理
    fun insertDogData(dogData: dogData){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dogDataBase.dogDataDao().insertDogData(dogData)
            }catch (e: Exception) {
                Log.e("PictureViewModel", "Error inserting dogData", e)
            }
        }
    }
    fun deleteDogData(dogData: dogData){
        viewModelScope.launch(Dispatchers.IO) {
            dogDataBase.dogDataDao().deleteDogData(dogData)
        }
    }
}