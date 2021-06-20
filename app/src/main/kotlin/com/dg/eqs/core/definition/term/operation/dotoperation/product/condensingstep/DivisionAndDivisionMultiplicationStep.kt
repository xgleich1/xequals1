package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.DivisionAndDivisionExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.DivisionAndDivisionMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.DivisionAndDivisionMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor.DivisionAndDivisionMultiplication


object DivisionAndDivisionMultiplicationStep : CondensingStep<Division, Division>(
        DivisionAndDivisionMatcher,
        DivisionAndDivisionExtractor,
        DivisionAndDivisionMapper,
        DivisionAndDivisionMultiplication)