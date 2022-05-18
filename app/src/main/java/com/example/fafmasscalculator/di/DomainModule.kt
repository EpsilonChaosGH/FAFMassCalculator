package com.example.fafmasscalculator.di

import com.example.fafmasscalculator.domain.usercase.GetMassAndMpsUseCase
import com.example.fafmasscalculator.domain.usercase.GetResultListUseCase
import com.example.fafmasscalculator.domain.usercase.SaveMassAndMpsUseCase
import org.koin.dsl.module



val domainModule = module {

    factory<GetResultListUseCase> {
        GetResultListUseCase(resultListRepository = get())
    }

    factory<GetMassAndMpsUseCase> {
        GetMassAndMpsUseCase(massAndMpsRepository = get())
    }

    factory<SaveMassAndMpsUseCase> {
        SaveMassAndMpsUseCase(massAndMpsRepository = get())
    }
}