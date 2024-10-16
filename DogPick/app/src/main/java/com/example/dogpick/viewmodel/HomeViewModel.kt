package com.example.dogpick.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogpick.data.dogData
import com.example.dogpick.data.dogDataS
import com.example.dogpick.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel (): ViewModel() {

    private var randomDogLiveData = MutableLiveData<dogData>()
    private var dogDataSItemLiveData = MutableLiveData<dogDataS>()
    //画面回転する時更新させない為に
    val saveStateOfRandomDog = MutableLiveData<dogData>()
    val saveStateOfDogDataSItem = MutableLiveData<dogDataS>()


    fun getRandomDogData() {
        saveStateOfRandomDog.value?.let {
            randomDogLiveData.postValue(it)
            return
        }
        RetrofitInstance.api.getRandomDogData().enqueue(object : Callback<dogData> {
            override fun onResponse(call: Call<dogData>, response: Response<dogData>) {
                if (response.body() != null) {
                    val randomdogData: dogData = response.body()!!
                    randomDogLiveData.value = randomdogData
                    saveStateOfRandomDog.value = randomdogData
                } else {
                    return
                }
            }
            override fun onFailure(call: Call<dogData>, t: Throwable) {
                Log.d("getRandomDogDataonFailure", t.message.toString())
            }
        })
    }

    fun getDogDataSItem() {
        saveStateOfDogDataSItem.value?.let {
            dogDataSItemLiveData.postValue(it)
            return
        }
        RetrofitInstance.api.getDogDataS().enqueue(object : Callback<dogDataS> {
            override fun onResponse(call: Call<dogDataS>, response: Response<dogDataS>) {
                if (response.body() != null) {
                    val dogDataSItem: dogDataS = response.body()!!
                    dogDataSItemLiveData.value = dogDataSItem
                    saveStateOfDogDataSItem.value = dogDataSItem
                } else {
                    return
                }
            }
            override fun onFailure(call: Call<dogDataS>, t: Throwable) {
                Log.e("getDogDataSItemFailure", "${t.message}")
            }
        })
    }

    //LiveData　中の貰うだけ
    fun getRandomDogLiveData(): LiveData<dogData> {
        return randomDogLiveData

    }

    fun getDogDataSItemLiveData(): LiveData<dogDataS> {
        return dogDataSItemLiveData

    }
}