package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.AnythingAndDivisionExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.AnythingAndDivisionMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.AnythingAndDivisionMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor.AnythingAndDivisionMultiplication


object AnythingAndDivisionMultiplicationStep : CondensingStep<Terms, Division>(
        AnythingAndDivisionMatcher,
        AnythingAndDivisionExtractor,
        AnythingAndDivisionMapper,
        AnythingAndDivisionMultiplication)