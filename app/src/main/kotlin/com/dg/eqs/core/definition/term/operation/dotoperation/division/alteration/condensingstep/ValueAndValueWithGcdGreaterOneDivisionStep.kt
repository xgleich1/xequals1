package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.ValueAndValueExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.ValueAndValueMapper
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor.ValueAndValueWithGcdGreaterOneDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.matcher.ValueAndValueWithGcdGreaterOneInDivisionMatcher


object ValueAndValueWithGcdGreaterOneDivisionStep : CondensingStep<Value, Value>(
        ValueAndValueWithGcdGreaterOneInDivisionMatcher,
        ValueAndValueExtractor,
        ValueAndValueMapper,
        ValueAndValueWithGcdGreaterOneDivision)