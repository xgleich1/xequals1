package com.dg.eqs.core.visualization

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.mapping.OriginToDraftMapper


abstract class Drafter(
        private val mapper: OriginToDraftMapper,
        private val pencil: Pencil) {

    fun draft(origin: AnyOrigin) = mapper
            .mapToDraft(origin)
            .draft(pencil)
}