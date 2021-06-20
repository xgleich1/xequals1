package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.ZeroAndAnythingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.ValueAndTermMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.ZeroAndAnythingMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor.ZeroAndAnythingDivision


object ZeroAndAnythingDivisionStep : CondensingStep<Value, Term>(
        ZeroAndAnythingMatcher,
        ZeroAndAnythingExtractor,
        ValueAndTermMapper,
        ZeroAndAnythingDivision)