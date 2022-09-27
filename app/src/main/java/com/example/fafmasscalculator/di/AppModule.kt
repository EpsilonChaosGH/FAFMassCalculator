package com.example.fafmasscalculator.di

import com.example.fafmasscalculator.presentation.MenuVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MenuVM> {
        MenuVM(
            getParamsUseCase = get(),
            getResultListUseCase = get(),
            saveParamsUseCase = get(),
        )
    }
}