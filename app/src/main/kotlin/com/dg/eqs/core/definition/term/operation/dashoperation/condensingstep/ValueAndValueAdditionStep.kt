package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.ValueAndValueExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.ValueAndValueMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.ValueAndValueMatcher
import com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor.ValueAndValueAddition


object ValueAndValueAdditionStep : CondensingStep<Value, Value>(
        ValueAndValueMatcher,
        ValueAndValueExtractor,
        ValueAndValueMapper,
        ValueAndValueAddition)