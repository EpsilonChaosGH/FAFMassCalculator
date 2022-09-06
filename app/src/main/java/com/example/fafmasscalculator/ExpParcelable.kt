package com.example.fafmasscalculator

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ExpParcelable(
    var imageId: Int,
    var title: String,
    var mass: String,
    var type: String
) : Parcelable