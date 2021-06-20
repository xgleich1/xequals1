package com.dg.eqs.base.abbreviation

import com.dg.eqs.core.definition.Origin
import com.dg.eqs.core.definition.Parent
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingStep
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.progression.Level
import com.dg.eqs.core.visualization.Draft
import com.dg.eqs.core.visualization.Drafts
import com.dg.eqs.core.visualization.composition.CompositeDraft
import com.dg.eqs.core.visualization.symbolization.SymbolDraft
import com.dg.eqs.core.visualization.symbolization.line.sign.LineSign
import com.dg.eqs.core.visualization.symbolization.text.TextSymbol
import com.dg.eqs.core.visualization.symbolization.text.item.TextItem
import com.dg.eqs.core.visualization.symbolization.text.sign.TextSign


typealias AnyLevel = Level<*>

typealias AnyOrigin = Origin<*>

typealias AnyShiftingStep = ShiftingStep<*, *>

typealias AnyCondensingStep = CondensingStep<*, *>

typealias AnyDraft = Draft<*>

typealias AnyDrafts = Drafts<*>

typealias TermDraft = Draft<Term>

typealias TermDrafts = Drafts<Term>

typealias OperationDraft = Draft<Operation>

typealias AnyCompositeDraft = CompositeDraft<*>

typealias AnySymbolDraft = SymbolDraft<*>

typealias AnySymbolDrafts = List<AnySymbolDraft>

typealias AnyLineSign = LineSign<*>

typealias AnyTextSymbol = TextSymbol<*>

typealias AnyTextItem = TextItem<*>

typealias AnyTextSign = TextSign<*>

typealias AnyTextSigns = List<AnyTextSign>

typealias Parents = List<Parent>