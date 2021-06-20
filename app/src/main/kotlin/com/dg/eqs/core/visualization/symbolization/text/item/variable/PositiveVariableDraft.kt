package com.dg.eqs.core.visualization.symbolization.text.item.variable

import com.dg.eqs.core.definition.term.item.variable.Variable
import com.dg.eqs.core.visualization.symbolization.text.item.TextItem


class PositiveVariableDraft(origin: Variable) : TextItem<Variable>(origin, origin.unsignedName)