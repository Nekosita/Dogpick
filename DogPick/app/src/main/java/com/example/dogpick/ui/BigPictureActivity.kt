package com.example.dogpick.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dogpick.databinding.ActivityBigPictureBinding
import com.example.dogpick.ui.fragments.FavDogFragment
import com.example.dogpick.ui.fragments.HomeFragment
import com.example.dogpick.ui.fragments.HomeFragment.Companion.DOG_DATA

class BigPictureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBigPictureBinding
    private lateinit var dogDataFromFavDogFragment: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //初期化
        binding= ActivityBigPictureBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getDogDataFromFavFragment()
        setInformationInView()

    }


    private fun getDogDataFromFavFragment() {
        val intent = intent
        dogDataFromFavDogFragment=intent.getStringExtra(FavDogFragment.DOG_DATA)!!
    }

    private fun setInformationInView() {
        Glide.with(this).load(dogDataFromFavDogFragment).into(binding.dodPicInBig)
    }



}