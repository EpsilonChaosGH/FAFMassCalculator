package com.example.fafmasscalculator.data.storage

import android.content.Context
import android.content.SharedPreferences

private const val SHARED_PREFERENCES = "SHARED_PREFERENCES"
private const val PREF_MASS_COST_VALUE = "PREF_MASS_COST_VALUE"
private const val PREF_MASS_INCOME_VALUE = "PREF_MASS_INCOME_VALUE"

class SharedPrefParamsStorage(context: Context) : ParamsStorage {

    private var preferences: SharedPreferences = context.getSharedPreferences(
        SHARED_PREFERENCES,
        Context.MODE_PRIVATE
    )

    override fun save(saveParam: ParamsData) {
        preferences.edit()
            .putInt(PREF_MASS_COST_VALUE, saveParam.massCost)
            .putInt(PREF_MASS_INCOME_VALUE, saveParam.massIncome)
            .apply()
    }

    override fun get(): ParamsData {
        return ParamsData(
            massCost = preferences.getInt(PREF_MASS_COST_VALUE, 150000),
            massIncome = (preferences.getInt(PREF_MASS_INCOME_VALUE, 150))
        )
    }

}