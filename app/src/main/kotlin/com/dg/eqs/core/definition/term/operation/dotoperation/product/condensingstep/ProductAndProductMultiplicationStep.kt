package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.ProductAndProductExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.ProductAndProductMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.ProductAndProductMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor.ProductAndProductMultiplication


object ProductAndProductMultiplicationStep : CondensingStep<Product, Product>(
        ProductAndProductMatcher,
        ProductAndProductExtractor,
        ProductAndProductMapper,
        ProductAndProductMultiplication)