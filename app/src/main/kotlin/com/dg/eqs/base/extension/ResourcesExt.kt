package com.dg.eqs.base.extension

import android.content.res.Resources
import androidx.annotation.ArrayRes


fun Resources.getStrings(@ArrayRes stringsResId: Int) =
        getStringArray(stringsResId).toList()