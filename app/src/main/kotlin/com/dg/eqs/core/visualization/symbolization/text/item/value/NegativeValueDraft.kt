package com.dg.eqs.core.visualization.symbolization.text.item.value

import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.visualization.symbolization.text.item.TextItem


class NegativeValueDraft(origin: Value) : TextItem<Value>(origin, origin.toText()) {
    private companion object TextCreator {
        private fun Value.toText() =
                if(unsignedNumber != 0) {
                    "-$unsignedNumber"
                } else {
                    "$unsignedNumber"
                }
    }
}