package com.dg.eqs.base.extension


infix fun <L, R> L.and(right: R) = Pair(this, right)