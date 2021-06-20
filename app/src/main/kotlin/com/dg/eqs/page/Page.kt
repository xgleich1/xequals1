package com.dg.eqs.page

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.dg.eqs.Eqs


abstract class Page(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId) {
    protected val applicationComponent
        get() = (application as Eqs).applicationComponent
}