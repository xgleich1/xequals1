package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.OneAndAnythingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.AnythingAndAnythingMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.OneAndAnythingMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor.OneAndAnythingMultiplication


object OneAndAnythingMultiplicationStep : CondensingStep<Terms, Terms>(
        OneAndAnythingMatcher,
        OneAndAnythingExtractor,
        AnythingAndAnythingMapper,
        OneAndAnythingMultiplication)