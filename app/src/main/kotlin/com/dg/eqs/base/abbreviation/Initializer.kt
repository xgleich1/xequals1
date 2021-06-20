package com.dg.eqs.base.abbreviation

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.interaction.linking.choosing.Choice
import com.dg.eqs.core.visualization.Draft
import com.dg.eqs.core.visualization.Drafts


fun termsOf(terms: List<Term>) = Terms(terms)

fun termsOf(vararg terms: Term?) = Terms(terms.filterNotNull())

fun termsOf(terms: List<Term>, term: Term) = Terms(terms + term)

fun termsOf(term: Term, terms: List<Term>) = Terms(listOf(term) + terms)

fun valueOf(number: Int) = if(number < 0) NegativeValue(-number) else PositiveValue(number)

fun <T : AnyOrigin> draftsOf() = Drafts(emptyList<Draft<T>>())

fun <T : AnyOrigin> draftsOf(drafts: List<Draft<T>>) = Drafts(drafts)

fun <T : AnyOrigin> draftsOf(vararg drafts: Draft<T>?) = Drafts(drafts.filterNotNull())

fun <T : AnyOrigin> draftsOf(vararg drafts: List<Draft<T>>) = Drafts(drafts.toList().flatten())

fun <T : AnyOrigin> draftsOf(drafts: List<Draft<T>>, draft: Draft<T>) = Drafts(drafts + draft)

fun <T : AnyOrigin> draftsOf(draft: Draft<T>, drafts: List<Draft<T>>) = Drafts(listOf(draft) + drafts)

fun symbolsOf(vararg symbols: AnySymbolDraft) = symbols.toList()

fun choiceOf(vararg chosen: TermDraft) = Choice(draftsOf(*chosen))