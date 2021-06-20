package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.DivisionAndAnythingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.DivisionAndAnythingMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.DivisionAndAnythingMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor.DivisionAndAnythingMultiplication


object DivisionAndAnythingMultiplicationStep : CondensingStep<Division, Terms>(
        DivisionAndAnythingMatcher,
        DivisionAndAnythingExtractor,
        DivisionAndAnythingMapper,
        DivisionAndAnythingMultiplication)