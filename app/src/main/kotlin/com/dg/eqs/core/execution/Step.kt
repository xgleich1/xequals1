package com.dg.eqs.core.execution

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.information.Info
import com.dg.eqs.core.information.invalid.InvalidInfo
import com.dg.eqs.core.information.valid.ValidInfo


sealed class Step {
    abstract val info: Info
    abstract val origin: AnyOrigin
    abstract val isValid: Boolean

    val isInvalid get() = !isValid


    data class ValidStep(
            override val info: ValidInfo,
            override val origin: AnyOrigin) : Step() {

        override val isValid = true
    }

    data class InvalidStep(
            override val info: InvalidInfo,
            override val origin: AnyOrigin) : Step() {

        override val isValid = false
    }
}