package com.example.dogpick.di

import com.example.dogpick.database.dogDataBase
import com.example.dogpick.viewmodel.FavDogViewModel
import com.example.dogpick.viewmodel.PictureViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        dogDataBase.create(androidContext())
    }

    viewModel { PictureViewModel(get())}

    viewModel { FavDogViewModel(get())}

}