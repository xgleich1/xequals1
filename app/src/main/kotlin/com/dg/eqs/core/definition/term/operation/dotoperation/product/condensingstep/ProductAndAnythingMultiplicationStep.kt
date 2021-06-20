package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.ProductAndAnythingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.ProductAndAnythingMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.ProductAndAnythingMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor.ProductAndAnythingMultiplication


object ProductAndAnythingMultiplicationStep : CondensingStep<Product, Terms>(
        ProductAndAnythingMatcher,
        ProductAndAnythingExtractor,
        ProductAndAnythingMapper,
        ProductAndAnythingMultiplication)