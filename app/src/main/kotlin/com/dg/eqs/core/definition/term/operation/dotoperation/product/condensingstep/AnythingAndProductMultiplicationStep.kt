package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.AnythingAndProductExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.AnythingAndProductMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.AnythingAndProductMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor.AnythingAndProductMultiplication


object AnythingAndProductMultiplicationStep : CondensingStep<Terms, Product>(
        AnythingAndProductMatcher,
        AnythingAndProductExtractor,
        AnythingAndProductMapper,
        AnythingAndProductMultiplication)