package com.example.fafmasscalculator.di

import com.example.fafmasscalculator.domain.usercase.GetParamsUseCase
import com.example.fafmasscalculator.domain.usercase.GetResultListUseCase
import com.example.fafmasscalculator.domain.usercase.SaveParamsUseCase
import org.koin.dsl.module


val domainModule = module {

    factory<GetResultListUseCase> {
        GetResultListUseCase(resultListRepository = get())
    }

    factory<GetParamsUseCase> {
        GetParamsUseCase(paramsRepository = get())
    }

    factory<SaveParamsUseCase> {
        SaveParamsUseCase(paramsRepository = get())
    }
}