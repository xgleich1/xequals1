package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.TermAndEqualTermExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.TermAndTermMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.TermAndEqualTermMatcher
import com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor.TermAndEqualTermAddition


object TermAndEqualTermAdditionStep : CondensingStep<Term, Term>(
        TermAndEqualTermMatcher,
        TermAndEqualTermExtractor,
        TermAndTermMapper,
        TermAndEqualTermAddition)