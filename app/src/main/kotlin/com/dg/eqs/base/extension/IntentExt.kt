package com.dg.eqs.base.extension

import android.content.Intent
import android.os.Parcelable


inline fun <reified T : Parcelable> Intent.getParcelableExtraOrThrow(name: String) =
        requireNotNull(getParcelableExtra<T>(name))