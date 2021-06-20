package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.AnythingAndZeroExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.AnythingAndAnythingMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.AnythingAndZeroMatcher
import com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor.AnythingAndZeroAddition


object AnythingAndZeroAdditionStep : CondensingStep<Terms, Terms>(
        AnythingAndZeroMatcher,
        AnythingAndZeroExtractor,
        AnythingAndAnythingMapper,
        AnythingAndZeroAddition)