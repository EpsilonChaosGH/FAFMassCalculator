package com.example.fafmasscalculator.data.storage

import android.content.Context
import android.content.SharedPreferences

private const val SHARED_PREFERENCES = "SHARED_PREFERENCES"
private const val PREF_MPS_VALUE = "PREF_MPS_VALUE"
private const val PREF_MASSNEED_VALUE = "PREF_MASSNEED_VALUE"

class SharedPrefMassAndMpsStorage(context: Context): MassAndMpsStorage {

    private var preferences : SharedPreferences = context.getSharedPreferences(
        SHARED_PREFERENCES,
        Context.MODE_PRIVATE)

    override fun save(saveParam : MassAndMpsStor){
        preferences.edit()
            .putInt(PREF_MASSNEED_VALUE,saveParam.mass)
            .putInt(PREF_MPS_VALUE,saveParam.mps)
            .apply()
    }
    override fun get(): MassAndMpsStor {
        return MassAndMpsStor(mass = preferences.getInt(PREF_MASSNEED_VALUE,1), mps = (preferences.getInt(PREF_MPS_VALUE,1)))
    }

}