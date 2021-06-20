package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.DivisionAndAnythingMapper
import com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor.DivisionThroughPositiveOneAndAnythingAddition
import com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.extractor.DivisionThroughPositiveOneAndAnythingExtractor
import com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.matcher.DivisionThroughPositiveOneAndAnythingMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division


object DivisionThroughPositiveOneAndAnythingAdditionStep : CondensingStep<Division, Terms>(
        DivisionThroughPositiveOneAndAnythingMatcher,
        DivisionThroughPositiveOneAndAnythingExtractor,
        DivisionAndAnythingMapper,
        DivisionThroughPositiveOneAndAnythingAddition)