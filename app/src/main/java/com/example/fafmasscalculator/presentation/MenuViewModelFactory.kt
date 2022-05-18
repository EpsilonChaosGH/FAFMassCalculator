package com.example.fafmasscalculator.presentation

//class MenuViewModelFactory(context: Context) : ViewModelProvider.Factory {
//
//    private val resultListRepository by lazy { ResultListRepositoryImpl() }
//    private val massAndMpsRepository by lazy { MassAndMpsRepositoryImpl(SharedPrefMassAndMpsStorage(context)) }
//    private val getResultListUseCase by lazy { GetResultListUseCase(resultListRepository) }
//    private val getMassAndMpsUseCase by lazy { GetMassAndMpsUseCase(massAndMpsRepository) }
//    private val saveMassAndMpsUseCase by lazy { SaveMassAndMpsUseCase(massAndMpsRepository) }
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return MenuVM(
//            getResultListUseCase = getResultListUseCase,
//            getMassAndMpsUseCase = getMassAndMpsUseCase,
//            saveMassAndMpsUseCase = saveMassAndMpsUseCase
//        ) as T
//    }
//}