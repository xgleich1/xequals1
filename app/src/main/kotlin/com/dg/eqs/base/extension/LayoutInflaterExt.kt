package com.dg.eqs.base.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dg.eqs.base.enveloping.LayoutRes


fun LayoutInflater.inflate(layout: LayoutRes, root: ViewGroup?): View = inflate(layout.resId, root)