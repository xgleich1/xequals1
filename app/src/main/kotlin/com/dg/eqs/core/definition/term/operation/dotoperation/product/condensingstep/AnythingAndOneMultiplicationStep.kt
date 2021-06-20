package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.AnythingAndOneExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.AnythingAndAnythingMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.AnythingAndOneMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor.AnythingAndOneMultiplication


object AnythingAndOneMultiplicationStep : CondensingStep<Terms, Terms>(
        AnythingAndOneMatcher,
        AnythingAndOneExtractor,
        AnythingAndAnythingMapper,
        AnythingAndOneMultiplication)