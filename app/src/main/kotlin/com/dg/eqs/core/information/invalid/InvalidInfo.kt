package com.dg.eqs.core.information.invalid

import com.dg.eqs.core.information.Info


abstract class InvalidInfo : Info() {
    override val isValid = false
}