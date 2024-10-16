package com.example.dogpick.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dogpick.databinding.ActivityPictureBinding
import com.example.dogpick.data.dogData
import com.example.dogpick.ui.fragments.HomeFragment
import com.example.dogpick.viewmodel.PictureViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PictureActivity : AppCompatActivity() {

    private lateinit var dogDataFromHomeFragment: String
    private lateinit var DataToDataBase : String
    private lateinit var binding: ActivityPictureBinding
    private val pictureViewModel: PictureViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初期化
        binding= ActivityPictureBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getDogDataFromHomeFragment()
        setInformationInView()


        onFavButtonClick()
    }


    private fun onFavButtonClick() {
       binding.putIntoFav.setOnClickListener {
           //データをデータベースの中に入ります
           val newDogData = dogData(message = DataToDataBase , status = "true")
           pictureViewModel.insertDogData(newDogData)
           Toast.makeText(this, "GETだせ＞w＜", Toast.LENGTH_SHORT).show();
           Log.d("PictureActivity", "onFavButtonClick: Called")
       }
    }


    private fun getDogDataFromHomeFragment() {
        val intent = intent
        dogDataFromHomeFragment=intent.getStringExtra(HomeFragment.DOG_DATA)!!
        DataToDataBase=dogDataFromHomeFragment

    }
    private fun setInformationInView() {
      Glide.with(this).load(dogDataFromHomeFragment).into(binding.imgDogBig)
    }

}