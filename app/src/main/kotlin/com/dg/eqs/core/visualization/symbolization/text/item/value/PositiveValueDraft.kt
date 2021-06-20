package com.dg.eqs.core.visualization.symbolization.text.item.value

import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.visualization.symbolization.text.item.TextItem


class PositiveValueDraft(origin: Value) : TextItem<Value>(origin, "${origin.unsignedNumber}")