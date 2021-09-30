package com.icebeal.myflix.di

import com.icebeal.core.domain.MovieInteractor
import com.icebeal.core.domain.MovieUseCase
import com.icebeal.myflix.detail.DetailViewModel
import com.icebeal.myflix.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {

    factory<MovieUseCase> { MovieInteractor(get()) }

}

val viewModelModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }

}