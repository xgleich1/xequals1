package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.ZeroAndAnythingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.AnythingAndAnythingMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.ZeroAndAnythingMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor.ZeroAndAnythingMultiplication


object ZeroAndAnythingMultiplicationStep : CondensingStep<Terms, Terms>(
        ZeroAndAnythingMatcher,
        ZeroAndAnythingExtractor,
        AnythingAndAnythingMapper,
        ZeroAndAnythingMultiplication)