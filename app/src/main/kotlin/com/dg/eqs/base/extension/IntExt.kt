package com.dg.eqs.base.extension


val Int.isEven get() = this % 2 == 0

val Int.isOdd get() = !isEven