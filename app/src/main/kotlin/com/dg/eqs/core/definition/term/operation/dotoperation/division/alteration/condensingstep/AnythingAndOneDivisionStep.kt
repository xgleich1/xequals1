package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.AnythingAndOneExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.TermAndValueMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.AnythingAndOneMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor.AnythingAndOneDivision


object AnythingAndOneDivisionStep : CondensingStep<Term, Value>(
        AnythingAndOneMatcher,
        AnythingAndOneExtractor,
        TermAndValueMapper,
        AnythingAndOneDivision)