package com.example.fafmasscalculator.data.storage

interface ParamsStorage {

    fun save(saveParam: ParamsData)

    fun get(): ParamsData

}