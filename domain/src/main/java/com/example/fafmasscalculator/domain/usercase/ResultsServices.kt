package com.example.fafmasscalculator.domain.usercase

import com.example.fafmasscalculator.domain.models.ResultList

typealias ResultListener = (results: ResultList) -> Unit

class ResultsServices {

    var listeners = mutableSetOf<ResultListener>()
    var results = ResultList()

    fun addResult(result: ResultList) {
        results.resultList = result.resultList
    }

    fun addListener(listener: ResultListener) {
        listeners.add(listener)
        listener.invoke(results)
    }

    fun removeListeners(listener: ResultListener) {
        listeners.remove(listener)
    }

    fun notifyChanges() {
        listeners.forEach { it.invoke(results) }
    }
}